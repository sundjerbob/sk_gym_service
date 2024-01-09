package raf.sk.sk_gym_service.entity_model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "gyms")
public class Gym {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String name;

    @Column(name = "number_of_trainers")
    Integer numberOfTrainers;

    @Column(name = "manager_email")
    String managersEmail;

    @OneToMany(mappedBy = "gym", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ScheduledWorkout> scheduledWorkouts;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "gym_training_types",
            joinColumns = @JoinColumn(name = "gym_id"),
            inverseJoinColumns = @JoinColumn(name = "training_type_id")
    )
    private List<GymTrainingType> supportedTrainingTypes;


}
