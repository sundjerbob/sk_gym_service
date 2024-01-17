package raf.sk.sk_gym_service.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
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
import raf.sk.sk_gym_service.user_external_service.helper.MessageHelper;
import raf.sk.sk_gym_service.user_external_service.inter_service_comunication.BookedWorkoutsIncrementDto;
import raf.sk.sk_gym_service.user_external_service.inter_service_comunication.EmailNotificationDto;
import raf.sk.sk_gym_service.user_external_service.inter_service_comunication.UserPerksDto;

import java.util.List;
import java.util.Optional;

@Service
public class BookedWorkoutService implements BookedWorkoutServiceApi {

    private final BookedWorkoutRepository bookedWorkoutRepository;
    private final ScheduledWorkoutRepository scheduledWorkoutRepository;
    private final MessageHelper messageHelper;
    private final GymTrainingTypeRepository gymTrainingTypeRepository;
    private final JWTServiceApi jwtService;

    @Value("${destination.incrementBookedWorkouts}")
    private String userServiceListenerQue;

    @Value("${destination.sendNotification}")
    private String notificationServiceListenerQue;
    private final UserServiceClient userServiceClient;

    private JmsTemplate jmsTemplate;

    public BookedWorkoutService(
            BookedWorkoutRepository bookedWorkoutRepository,
            ScheduledWorkoutRepository scheduledWorkoutRepository, MessageHelper messageHelper,
            GymTrainingTypeRepository gymTrainingTypeRepository,
            JWTServiceApi jwtService,
            UserServiceClient userServiceClient
    ) {
        this.bookedWorkoutRepository = bookedWorkoutRepository;
        this.scheduledWorkoutRepository = scheduledWorkoutRepository;
        this.messageHelper = messageHelper;
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

        GymTrainingType gymTrainingType = gymTrainingTypeOptional.get();

        // check if the scheduled workout capacity is already full
        if (scheduledWorkoutParent.getBookedWorkouts().size() >= gymTrainingType.getTrainingType().getMaxParticipants())
            throw new RuntimeException("The workout you are trying to book has no free spots left. Chose another scheduled workout.");

        UnpackedAuthToken authInfo = jwtService.unpackClaimsInfo(authHeader.split(" +")[1]);

        // fetch the user props from external user service via configured Http template client
        UserPerksDto userPerksDto = userServiceClient.getUserPerksWithRetry(
                authHeader, authInfo.getRequesterId(), scheduledWorkoutParent.getGym().getName()
        );

        // check if the client has free workout based on his perks
        Double workoutPrice =
                userPerksDto.getBookedWorkouts() % scheduledWorkoutParent.getGym().getEveryNthTrainingFree() == 0 ?
                        0.0 : gymTrainingType.getPrice();

        // create new booking record
        BookedWorkout bookedWorkout = new BookedWorkout();
        // link booking to a parent scheduled workout
        bookedWorkout.setScheduledWorkout(scheduledWorkoutParent);
        // link the user who made the booking using his email address
        bookedWorkout.setUserEmail(authInfo.getRequesterEmail());
        // set the calculated price to the booked workout
        bookedWorkout.setCalculatedPrice(workoutPrice);

        // trigger client stats increment inside external user service, send message via message broker
        BookedWorkoutsIncrementDto incrementMessage = new BookedWorkoutsIncrementDto();
        incrementMessage.setClientId(authInfo.getRequesterId());
        incrementMessage.setGymName(scheduledWorkoutParent.getGym().getName());
        jmsTemplate.convertAndSend(userServiceListenerQue, messageHelper.createTextMessage(incrementMessage));

        // send the notification to email sending service via message broker
        EmailNotificationDto emailNotification = new EmailNotificationDto();
        emailNotification.setEmailToNotify(authInfo.getRequesterEmail());
        emailNotification.setSubject("You have successfully booked a workout spot.");
        emailNotification.setMessage("Workout starting at : " + scheduledWorkoutParent.getStartTime() + "\nWorkout type " + gymTrainingType.getTrainingType().toString());
        jmsTemplate.convertAndSend(notificationServiceListenerQue, messageHelper.createTextMessage(emailNotification));

        // save persist booking and return created entity data
        return ObjectMapper.bookedWorkoutToDto(bookedWorkoutRepository.save(bookedWorkout));
    }


}
