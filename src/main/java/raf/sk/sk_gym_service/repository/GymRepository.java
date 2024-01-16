package raf.sk.sk_gym_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import raf.sk.sk_gym_service.entity_model.Gym;

import java.util.List;
import java.util.Optional;

@Repository
public interface GymRepository extends JpaRepository<Gym, Long> {
    // Find all gyms that are not deleted

    List<Gym> findAllByDeletedFalse();

    // Custom query to find a gym by ID that is not deleted
    Optional<Gym> findByIdAndDeletedFalse(Long id);

    Optional<Gym> findByName(String name);
    // Count all gyms that are not deleted
    long countByDeletedFalse();

    // Logical deletion by updating the deleted field
    void deleteById(Long id);
}
