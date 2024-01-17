package raf.sk.sk_gym_service.entity_model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "training_types")
public class TrainingType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;


    @Column(name = "duration_minutes")
    private Integer durationInMinutes;

    @Column
    private String description;


    @Column(name = "is_individual")
    private Boolean isIndividual;

    private Integer maxParticipants;


    @ManyToMany(mappedBy = "supportedTrainingTypes")
    private List<Gym> supportedGyms;


    @Override
    public String toString() {
        return "TrainingType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", durationInMinutes=" + durationInMinutes +
                ", description='" + description + '\'' +
                ", isIndividual=" + isIndividual +
                ", maxParticipants=" + maxParticipants +
                ", supportedGyms=" + supportedGyms +
                '}';
    }

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

    public Integer getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(Integer durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIndividual(Boolean individual) {
        isIndividual = individual;
    }

    public Boolean getIndividual() {
        return isIndividual;
    }

    public Integer getMaxParticipants() {
        return maxParticipants;
    }

    public void setMaxParticipants(Integer maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    public List<Gym> getSupportedGyms() {
        return supportedGyms;
    }

    public void setSupportedGyms(List<Gym> supportedGyms) {
        this.supportedGyms = supportedGyms;
    }
}
