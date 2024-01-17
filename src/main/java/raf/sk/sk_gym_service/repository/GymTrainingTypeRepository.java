package raf.sk.sk_gym_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import raf.sk.sk_gym_service.entity_model.GymTrainingType;

import java.util.Optional;

public interface GymTrainingTypeRepository extends JpaRepository<GymTrainingType, Long> {

    Optional<GymTrainingType> findByGymIdAndTrainingTypeId(Long gymId, Long trainingTypeId);

}
