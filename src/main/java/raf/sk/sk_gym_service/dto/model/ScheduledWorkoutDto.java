package raf.sk.sk_gym_service.dto.model;

import java.time.LocalDateTime;

public class ScheduledWorkoutDto {

    private LocalDateTime startTime;

    private String gymName;

    private TrainingTypeDto trainingType;

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public String getGymName() {
        return gymName;
    }

    public void setGymName(String gymName) {
        this.gymName = gymName;
    }

    public TrainingTypeDto getTrainingType() {
        return trainingType;
    }

    public void setTrainingType(TrainingTypeDto trainingType) {
        this.trainingType = trainingType;
    }
}
