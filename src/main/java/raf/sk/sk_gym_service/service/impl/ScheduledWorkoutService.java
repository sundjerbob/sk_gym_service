package raf.sk.sk_gym_service.service.impl;

import org.springframework.stereotype.Service;
import raf.sk.sk_gym_service.dto.ScheduledWorkoutDto;
import raf.sk.sk_gym_service.entity_model.Gym;
import raf.sk.sk_gym_service.entity_model.ScheduledWorkout;
import raf.sk.sk_gym_service.entity_model.TrainingType;
import raf.sk.sk_gym_service.object_mapper.ObjectMapper;
import raf.sk.sk_gym_service.repository.GymRepository;
import raf.sk.sk_gym_service.repository.ScheduledWorkoutRepository;
import raf.sk.sk_gym_service.repository.TrainingTypeRepository;
import raf.sk.sk_gym_service.service.api.ScheduledWorkoutServiceApi;

import java.util.List;
import java.util.Optional;

@Service
public class ScheduledWorkoutService implements ScheduledWorkoutServiceApi {

    private final ScheduledWorkoutRepository scheduledWorkoutRepository;
    private final TrainingTypeRepository trainingTypeRepository;

    private final GymRepository gymRepository;

    public ScheduledWorkoutService(ScheduledWorkoutRepository scheduledWorkoutRepository, TrainingTypeRepository trainingTypeRepository, GymRepository gymRepository) {
        this.scheduledWorkoutRepository = scheduledWorkoutRepository;
        this.trainingTypeRepository = trainingTypeRepository;
        this.gymRepository = gymRepository;
    }

    public ScheduledWorkoutDto getScheduledWorkoutById(Long id) {
        return scheduledWorkoutRepository.findById(id).map(
                ObjectMapper::scheduledWorkoutToDto
        ).orElse(null);
    }

    public List<ScheduledWorkoutDto> getScheduledWorkoutForGym(Long gymId) {
        return scheduledWorkoutRepository.findByGymIdOrderByStartTime(gymId).stream()
                .map(
                        ObjectMapper::scheduledWorkoutToDto
                ).toList();
    }


    public ScheduledWorkoutDto createScheduledWorkout(ScheduledWorkoutDto scheduledWorkoutDto) {
        Optional<Gym> gymOptional = gymRepository.findByName(scheduledWorkoutDto.getGymName());
        Optional<TrainingType> trainingTypeOptional = trainingTypeRepository.findById(scheduledWorkoutDto.getTrainingType().getId());
        if (gymOptional.isEmpty() || trainingTypeOptional.isEmpty())
            return null;

        ScheduledWorkout scheduledWorkout = new ScheduledWorkout();
        scheduledWorkout.setGym(gymOptional.get());
        scheduledWorkout.setTrainingType(trainingTypeOptional.get());
        scheduledWorkout.setStartTime(scheduledWorkoutDto.getStartTime());

        return ObjectMapper.scheduledWorkoutToDto(scheduledWorkoutRepository.save(scheduledWorkout));
    }

    public ScheduledWorkoutDto updateScheduledWorkout(Long id, ScheduledWorkoutDto updatedScheduledWorkout) {

        Optional<ScheduledWorkout> scheduledWorkoutOptional = scheduledWorkoutRepository.findById(id);
        Optional<Gym> gymOptional = gymRepository.findByName(updatedScheduledWorkout.getGymName());
        Optional<TrainingType> trainingTypeOptional = trainingTypeRepository.findById(updatedScheduledWorkout.getTrainingType().getId());

        if (scheduledWorkoutOptional.isEmpty() || gymOptional.isEmpty() || trainingTypeOptional.isEmpty())
            return null;

        ScheduledWorkout scheduledWorkout = scheduledWorkoutOptional.get();
        scheduledWorkout.setGym(gymOptional.get());
        scheduledWorkout.setTrainingType(trainingTypeOptional.get());
        scheduledWorkout.setStartTime(updatedScheduledWorkout.getStartTime());

        return ObjectMapper.scheduledWorkoutToDto(scheduledWorkoutRepository.save(scheduledWorkout));
    }

    public boolean deleteWorkoutSchedule(Long id) {
        if (scheduledWorkoutRepository.existsById(id)) {
            scheduledWorkoutRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean setCanceledTo(Long id, Boolean setCanceledTo) {
        return scheduledWorkoutRepository.setCanceledTo(id, setCanceledTo) == 1;
    }

}
