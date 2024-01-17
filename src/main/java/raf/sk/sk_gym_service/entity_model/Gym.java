package raf.sk.sk_gym_service.entity_model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "gyms")
public class Gym {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @Column(name = "number_of_trainers")
    private Integer numberOfTrainers;

    @Column(name = "manager_email")
    private String managerEmail;

    @Column(name = "free_training_delta")
    private Integer everyNthTrainingFree;
    @OneToMany(mappedBy = "gym", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ScheduledWorkout> scheduledWorkouts;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "gym_training_types",
            joinColumns = @JoinColumn(name = "gym_id"),
            inverseJoinColumns = @JoinColumn(name = "training_type_id")
    )
    private List<TrainingType> supportedTrainingTypes;

    private Boolean deleted;

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

    public String getManagerEmail() {
        return managerEmail;
    }

    public void setManagerEmail(String managerEmail) {
        this.managerEmail = managerEmail;
    }

    public List<ScheduledWorkout> getScheduledWorkouts() {
        return scheduledWorkouts;
    }

    public void setScheduledWorkouts(List<ScheduledWorkout> scheduledWorkouts) {
        this.scheduledWorkouts = scheduledWorkouts;
    }

    public List<TrainingType> getSupportedTrainingTypes() {
        return supportedTrainingTypes;
    }

    public void setSupportedTrainingTypes(List<TrainingType> supportedTrainingTypes) {
        this.supportedTrainingTypes = supportedTrainingTypes;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Integer getEveryNthTrainingFree() {
        return everyNthTrainingFree;
    }

    public void setEveryNthTrainingFree(Integer everyNthTrainingFree) {
        this.everyNthTrainingFree = everyNthTrainingFree;
    }
}
