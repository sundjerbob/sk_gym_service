package raf.sk.sk_gym_service.entity_model;

import jakarta.persistence.*;

@Entity
@Table(name = "gym_training_types")
public class GymTrainingType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "gym_id")
    private Gym gym;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "training_type_id")
    private TrainingType trainingType;

    @Column
    private Double price;

    public GymTrainingType() {
    }

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
