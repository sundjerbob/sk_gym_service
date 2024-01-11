package raf.sk.sk_gym_service.entity_model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "gyms")
public class Gym {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(name = "number_of_trainers")
    private Integer numberOfTrainers;

    @Column(name = "manager_email")
    private String managersEmail;

    @OneToMany(mappedBy = "gym", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ScheduledWorkout> scheduledWorkouts;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "gym_training_types",
            joinColumns = @JoinColumn(name = "gym_id"),
            inverseJoinColumns = @JoinColumn(name = "training_type_id")
    )
    private List<GymTrainingType> supportedTrainingTypes;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumberOfTrainers() {
        return numberOfTrainers;
    }

    public void setNumberOfTrainers(Integer numberOfTrainers) {
        this.numberOfTrainers = numberOfTrainers;
    }

    public String getManagersEmail() {
        return managersEmail;
    }

    public void setManagersEmail(String managersEmail) {
        this.managersEmail = managersEmail;
    }

    public List<ScheduledWorkout> getScheduledWorkouts() {
        return scheduledWorkouts;
    }

    public void setScheduledWorkouts(List<ScheduledWorkout> scheduledWorkouts) {
        this.scheduledWorkouts = scheduledWorkouts;
    }

    public List<GymTrainingType> getSupportedTrainingTypes() {
        return supportedTrainingTypes;
    }

    public void setSupportedTrainingTypes(List<GymTrainingType> supportedTrainingTypes) {
        this.supportedTrainingTypes = supportedTrainingTypes;
    }
}
