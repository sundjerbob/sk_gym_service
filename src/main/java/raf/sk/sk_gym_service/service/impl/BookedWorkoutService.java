package raf.sk.sk_gym_service.service.impl;

import org.springframework.stereotype.Service;
import raf.sk.sk_gym_service.authorization.jwt_service.api.JWTServiceApi;
import raf.sk.sk_gym_service.authorization.jwt_service.dto.UnpackedAuthToken;
import raf.sk.sk_gym_service.dto.BookedWorkoutDto;
import raf.sk.sk_gym_service.entity_model.BookedWorkout;
import raf.sk.sk_gym_service.entity_model.GymTrainingType;
import raf.sk.sk_gym_service.entity_model.ScheduledWorkout;
import raf.sk.sk_gym_service.object_mapper.ObjectMapper;
import raf.sk.sk_gym_service.repository.BookedWorkoutRepository;
import raf.sk.sk_gym_service.repository.GymTrainingTypeRepository;
import raf.sk.sk_gym_service.repository.ScheduledWorkoutRepository;
import raf.sk.sk_gym_service.service.api.BookedWorkoutServiceApi;
import raf.sk.sk_gym_service.user_external_service.client.UserServiceClient;
import raf.sk.sk_gym_service.user_external_service.inter_service_comunication.UserPerks;

import java.util.List;
import java.util.Optional;

@Service
public class BookedWorkoutService implements BookedWorkoutServiceApi {

    private final BookedWorkoutRepository bookedWorkoutRepository;
    private final ScheduledWorkoutRepository scheduledWorkoutRepository;

    private final GymTrainingTypeRepository gymTrainingTypeRepository;
    private final JWTServiceApi jwtService;
    private final UserServiceClient userServiceClient;

    public BookedWorkoutService(BookedWorkoutRepository bookedWorkoutRepository, ScheduledWorkoutRepository scheduledWorkoutRepository, GymTrainingTypeRepository gymTrainingTypeRepository, JWTServiceApi jwtService, UserServiceClient userServiceClient) {
        this.bookedWorkoutRepository = bookedWorkoutRepository;
        this.scheduledWorkoutRepository = scheduledWorkoutRepository;
        this.gymTrainingTypeRepository = gymTrainingTypeRepository;
        this.jwtService = jwtService;
        this.userServiceClient = userServiceClient;
    }

    public BookedWorkoutDto getBookedWorkoutById(Long id) {
        return bookedWorkoutRepository.findById(id)
                .map(ObjectMapper::bookedWorkoutToDto)
                .orElse(null);
    }


    @Override
    public List<BookedWorkoutDto> getBookedWorkoutsByScheduledWorkout(Long scheduledWorkoutId) {
        return bookedWorkoutRepository.findByScheduledWorkoutId(
                scheduledWorkoutId
        ).stream().map(ObjectMapper::bookedWorkoutToDto).toList();
    }


    @Override
    public BookedWorkoutDto createBookedWorkout(Long scheduledWorkoutId, String authHeader) {

        Optional<ScheduledWorkout> scheduledWorkoutOptional = scheduledWorkoutRepository.findById(scheduledWorkoutId);
        if (scheduledWorkoutOptional.isEmpty() || !authHeader.startsWith("Bearer "))
            return null;

        ScheduledWorkout scheduledWorkoutParent = scheduledWorkoutOptional.get();

        Optional<GymTrainingType> gymTrainingTypeOptional =
                gymTrainingTypeRepository.findByGymIdAndTrainingTypeId(
                        scheduledWorkoutParent.getGym().getId(),
                        scheduledWorkoutParent.getTrainingType().getId()
                );


        if (gymTrainingTypeOptional.isEmpty())
            throw new RuntimeException("Price for this training type in this gym has not been defined!");

        UnpackedAuthToken authInfo = jwtService.unpackClaimsInfo(authHeader.split(" +")[1]);

        UserPerks userPerks = userServiceClient.getUserPerksWithRetry(
                authHeader, authInfo.getRequesterId(), scheduledWorkoutParent.getGym().getName()
        );
        Double trainingPrice = gymTrainingTypeOptional.get().getPrice();

        BookedWorkout bookedWorkout = new BookedWorkout();

        // link booking to a parent scheduled workout
        bookedWorkout.setScheduledWorkout(scheduledWorkoutParent);
        // link the user who made the booking using his email address
        bookedWorkout.setUserEmail(authInfo.getRequesterEmail());

        // save persist booking and return created entity data
        return ObjectMapper.bookedWorkoutToDto(bookedWorkoutRepository.save(bookedWorkout));
    }


}
