package raf.sk.sk_gym_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import raf.sk.sk_gym_service.entity_model.Gym;

@Repository
public interface GymRepository extends JpaRepository<Gym, Long> {
    // Additional query methods if needed...
}
