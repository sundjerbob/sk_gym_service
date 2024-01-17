package raf.sk.sk_gym_service.object_mapper;

import raf.sk.sk_gym_service.dto.*;
import raf.sk.sk_gym_service.entity_model.*;


public class ObjectMapper {


    public static BookedWorkoutDto bookedWorkoutToDto(BookedWorkout dataSource) {
        BookedWorkoutDto dataDestination = new BookedWorkoutDto();
        dataDestination.setId(dataSource.getId());
        dataDestination.setScheduledWorkout(scheduledWorkoutToDto(dataSource.getScheduledWorkout()));
        dataDestination.setUserEmail(dataSource.getUserEmail());
        return dataDestination;
    }

    public static ScheduledWorkoutDto scheduledWorkoutToDto(ScheduledWorkout dataSource) {
        ScheduledWorkoutDto dataDestination = new ScheduledWorkoutDto();
        dataDestination.setId(dataSource.getId());
        dataDestination.setGymName(dataSource.getGym().getName());
        dataDestination.setStartTime(dataSource.getStartTime());
        dataDestination.setTrainingType(trainingTypeToDto(dataSource.getTrainingType()));
        return dataDestination;
    }

    public static TrainingTypeDto trainingTypeToDto(TrainingType dataSource) {
        TrainingTypeDto dataDestination = new TrainingTypeDto();
        dataDestination.setId(dataSource.getId());
        dataDestination.setName(dataSource.getName());
        dataDestination.setDescription(dataSource.getDescription());
        dataDestination.setIndividual(dataSource.getIndividual());
        dataDestination.setDurationInMinutes(dataSource.getDurationInMinutes());
        return dataDestination;
    }




    public static GymDto gymToDto(Gym dataSource) {
        GymDto dataDestination = new GymDto();
        dataDestination.setId(dataSource.getId());
        dataDestination.setName(dataSource.getName());
        dataDestination.setManagerEmail(dataSource.getManagerEmail());
        dataDestination.setNumberOfTrainers(dataSource.getNumberOfTrainers());
        dataDestination.setSupportedTrainingTypes(
                dataSource.getSupportedTrainingTypes().stream()
                        .map(
                                ObjectMapper::gymTrainingTypeToDto
                        ).toList()
        );
        return dataDestination;
    }


    public static GymTrainingTypeDto gymTrainingTypeToDto(GymTrainingType dataSource) {
        GymTrainingTypeDto dataDestination = new GymTrainingTypeDto();
        dataDestination.setId(dataSource.getId());
        dataDestination.setGymName(dataSource.getGym().getName());
        dataDestination.setPrice(dataDestination.getPrice());
        return dataDestination;
    }


}