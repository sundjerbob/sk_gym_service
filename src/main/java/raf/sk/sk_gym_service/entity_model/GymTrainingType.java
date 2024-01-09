package raf.sk.sk_gym_service.entity_model;

import jakarta.persistence.*;

@Entity
@Table(name = "gym_training_types")
public class GymTrainingType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "gym_id")
    private Gym gym;

    @ManyToOne
    @JoinColumn(name = "training_type_id")
    private TrainingType trainingType;

    @Column
    private Double price;

    // Constructors, getters, setters...
}
