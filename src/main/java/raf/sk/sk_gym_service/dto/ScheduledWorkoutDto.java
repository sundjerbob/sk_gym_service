package raf.sk.sk_gym_service.dto;

import raf.sk.sk_gym_service.entity_model.BookedWorkout;

import java.time.LocalDateTime;

public class ScheduledWorkoutDto {

    Long id;
    private LocalDateTime startTime;

    private String gymName;

    private TrainingTypeDto trainingType;

    private boolean isCanceled;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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



    public void setCanceled(boolean canceled) {
        isCanceled = canceled;
    }

    public boolean isCanceled() {
        return isCanceled;
    }
}
