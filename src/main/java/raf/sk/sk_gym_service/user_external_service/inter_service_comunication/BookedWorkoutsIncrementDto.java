package raf.sk.sk_gym_service.user_external_service.inter_service_comunication;

public class BookedWorkoutsIncrementDto {

    String gymName;

    Long clientId;

    public BookedWorkoutsIncrementDto() {
    }

    public String getGymName() {
        return gymName;
    }

    public void setGymName(String gymName) {
        this.gymName = gymName;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }
}
