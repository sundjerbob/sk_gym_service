package raf.sk.sk_gym_service.dto;

import raf.sk.sk_gym_service.entity_model.Gym;
import raf.sk.sk_gym_service.entity_model.TrainingType;

import java.time.LocalDateTime;

public class BookWorkoutRequest {


    private Gym gym;

    private TrainingType trainingType;

    private LocalDateTime startTime;

}
