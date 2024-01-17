package raf.sk.sk_gym_service.dto;

public class GymTrainingTypeDto {

    private Long id;
    private String gymName;

    private TrainingTypeDto trainingType;

    private Double price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
