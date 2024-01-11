package raf.sk.sk_gym_service.entity_model;

import jakarta.persistence.*;

@Entity
@Table(name = "booked_workouts")
public class BookedWorkout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "users_email", nullable = false)
    private String userEmail;

    @ManyToOne
    @JoinColumn(name = "scheduled_workout_id")
    private ScheduledWorkout scheduledWorkout;

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

    public ScheduledWorkout getScheduledWorkout() {
        return scheduledWorkout;
    }

    public void setScheduledWorkout(ScheduledWorkout scheduledWorkout) {
        this.scheduledWorkout = scheduledWorkout;
    }


}