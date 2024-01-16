package raf.sk.sk_gym_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import raf.sk.sk_gym_service.entity_model.BookedWorkout;

@Repository
public interface BookedWorkoutRepository extends JpaRepository<BookedWorkout, Long> {
    @Transactional
    @Modifying
    @Query("UPDATE BookedWorkout b SET b.isCanceled = :isCanceled WHERE b.id = :bookedWorkoutId")
    int setCanceledTo(@Param("bookedWorkoutId") Long bookedWorkoutId, @Param("isCanceled") Boolean isCanceled);
}





