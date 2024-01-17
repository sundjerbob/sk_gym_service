package raf.sk.sk_gym_service.entity_model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "scheduled_workouts")
public class ScheduledWorkout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "gym_id")
    private Gym gym;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "training_type_id")
    private TrainingType trainingType;

    @Column(columnDefinition = "DATETIME", name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "canceled")
    private Boolean isCanceled;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Gym getGym() {
        return gym;
    }

    public void setGym(Gym gym) {
        this.gym = gym;
    }

    public TrainingType getTrainingType() {
        return trainingType;
    }

    public void setTrainingType(TrainingType trainingType) {
        this.trainingType = trainingType;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setCanceled(Boolean canceled) {
        isCanceled = canceled;
    }

    public Boolean getCanceled() {
        return isCanceled;
    }
}