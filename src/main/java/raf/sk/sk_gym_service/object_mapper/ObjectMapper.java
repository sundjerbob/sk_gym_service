package raf.sk.sk_gym_service.object_mapper;

import raf.sk.sk_gym_service.dto.model.BookedWorkoutDto;
import raf.sk.sk_gym_service.dto.model.ScheduledWorkoutDto;
import raf.sk.sk_gym_service.dto.model.TrainingTypeDto;
import raf.sk.sk_gym_service.entity_model.BookedWorkout;
import raf.sk.sk_gym_service.entity_model.ScheduledWorkout;
import raf.sk.sk_gym_service.entity_model.TrainingType;

public class ObjectMapper {

    public static BookedWorkoutDto bookedWorkoutToDto(BookedWorkout dataSource, BookedWorkoutDto dataDestination) {
        dataDestination.setId(dataSource.getId());
        dataDestination.setScheduledWorkout(scheduledWorkoutToDto(dataSource.getScheduledWorkout(), new ScheduledWorkoutDto()));
        dataDestination.setUserEmail(dataSource.getUserEmail());
        return dataDestination;
    }

    public static ScheduledWorkoutDto scheduledWorkoutToDto(ScheduledWorkout dataSource, ScheduledWorkoutDto dataDestination) {
        dataDestination.setId(dataSource.getId());
        dataDestination.setGymName(dataSource.getGym().getName());
        dataDestination.setStartTime(dataSource.getStartTime());
        dataDestination.setTrainingType(trainingTypeToDto(dataSource.getTrainingType(), new TrainingTypeDto()));
        return dataDestination;
    }

    public static TrainingTypeDto trainingTypeToDto(TrainingType dataSource, TrainingTypeDto dataDestination) {
        dataDestination.setId(dataSource.getId());
        dataDestination.setName(dataSource.getName());
        dataDestination.setDescription(dataSource.getDescription());
        dataDestination.setIndividual(dataSource.isIndividual());
        dataDestination.setDurationInMinutes(dataSource.getDurationInMinutes());
        return dataDestination;
    }

    public static BookedWorkout dtoToBookedWorkout(BookedWorkoutDto dataSource, BookedWorkout dataDestination) {
        dataDestination.setId(dataSource.getId());
        dataDestination.setUserEmail(dataSource.getUserEmail());
        dataDestination.setScheduledWorkout(dtoToScheduledWorkout(dataSource.getScheduledWorkout(), new ScheduledWorkout()));
        return dataDestination;
    }

    public static ScheduledWorkout dtoToScheduledWorkout(ScheduledWorkoutDto dataSource, ScheduledWorkout dataDestination) {
        dataDestination.setId(dataSource.getId());
        dataDestination.setTrainingType(dtoToTrainingType(dataSource.getTrainingType(), new TrainingType()));
        dataDestination.setId(dataSource.getId());
        return dataDestination;
    }

    public static TrainingType dtoToTrainingType(TrainingTypeDto dataSource, TrainingType dataDestination) {
        dataDestination.setId(dataSource.getId());
        dataDestination.setName(dataSource.getName());
        dataDestination.setDescription(dataSource.getDescription());
        dataDestination.setIndividual(dataSource.isIndividual());
        dataDestination.setDurationInMinutes(dataSource.getDurationInMinutes());
        return dataDestination;
    }
}