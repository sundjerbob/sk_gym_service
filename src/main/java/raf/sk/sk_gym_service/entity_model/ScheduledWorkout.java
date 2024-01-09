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

    // Other fields, getters, and setters...

}