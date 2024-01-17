package raf.sk.sk_gym_service.dto;

import java.util.List;

public class GymDto {

    private Long id;
    private String name;

    private String managerEmail;

    private int numberOfTrainers;

    private List<TrainingTypeDto> supportedTrainingTypes;

    public List<TrainingTypeDto> getSupportedTrainingTypes() {
        return supportedTrainingTypes;
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

    public void setManagerEmail(String managerEmail) {
        this.managerEmail = managerEmail;
    }

    public String getManagerEmail() {
        return managerEmail;
    }

    public void setSupportedTrainingTypes(List<TrainingTypeDto> supportedTrainingTypes) {
        this.supportedTrainingTypes = supportedTrainingTypes;
    }


    public int getNumberOfTrainers() {
        return numberOfTrainers;
    }

    public void setNumberOfTrainers(int numberOfTrainers) {
        this.numberOfTrainers = numberOfTrainers;
    }
}
