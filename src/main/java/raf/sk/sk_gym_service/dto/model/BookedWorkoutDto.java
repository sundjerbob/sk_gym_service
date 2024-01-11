package raf.sk.sk_gym_service.dto.model;


public class BookedWorkoutDto {

    Long id;
    private String userEmail;

    private ScheduledWorkoutDto scheduledWorkout;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public ScheduledWorkoutDto getScheduledWorkout() {
        return scheduledWorkout;
    }

    public void setScheduledWorkout(ScheduledWorkoutDto scheduledWorkoutDto) {
        this.scheduledWorkout = scheduledWorkoutDto;
    }
}
