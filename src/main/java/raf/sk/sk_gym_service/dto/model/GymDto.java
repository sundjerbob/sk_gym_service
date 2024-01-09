package raf.sk.sk_gym_service.dto.model;

import java.util.List;

public class GymDto {

    private String name;

    private String managerEmail;

    private int numberOfTrainers;

    private List<GymTrainingTypeDto> supportedTrainingTypes;

    public List<GymTrainingTypeDto> getSupportedTrainingTypes() {
        return supportedTrainingTypes;
    }

    public void setSupportedTrainingTypes(List<GymTrainingTypeDto> supportedTrainingTypes) {
        this.supportedTrainingTypes = supportedTrainingTypes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getManagerEmail() {
        return managerEmail;
    }

    public void setManagerEmail(Long managerEmail) {
        this.managerEmail = managerEmail;
    }

    public int getNumberOfTrainers() {
        return numberOfTrainers;
    }

    public void setNumberOfTrainers(int numberOfTrainers) {
        this.numberOfTrainers = numberOfTrainers;
    }
}
