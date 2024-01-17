package raf.sk.sk_gym_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import raf.sk.sk_gym_service.entity_model.ScheduledWorkout;

import java.util.List;

@Repository
public interface ScheduledWorkoutRepository extends JpaRepository<ScheduledWorkout, Long> {

    List<ScheduledWorkout> findByGymIdOrderByStartTime(Long gymId);

    @Transactional
    @Modifying
    @Query("UPDATE ScheduledWorkout b SET b.isCanceled = :isCanceled WHERE b.id = :scheduledWorkoutId")
    int setCanceledTo(@Param("scheduledWorkoutId") Long scheduledWorkoutId, @Param("isCanceled") Boolean isCanceled);

}
