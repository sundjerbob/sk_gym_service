package raf.sk.sk_gym_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import raf.sk.sk_gym_service.entity_model.ScheduledWorkout;

import java.util.List;

@Repository
public interface ScheduledWorkoutRepository extends JpaRepository<ScheduledWorkout, Long> {

    List<ScheduledWorkout> findByGymIdOrderByStartTime(Long gymId);

}
