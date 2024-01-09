package raf.sk.sk_gym_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import raf.sk.sk_gym_service.entity_model.ScheduledWorkout;

@Repository
public interface WorkoutScheduleRepository extends JpaRepository<ScheduledWorkout, Long> {
    // Additional query methods if needed...
}
