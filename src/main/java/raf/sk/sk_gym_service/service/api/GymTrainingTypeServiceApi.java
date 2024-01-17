package raf.sk.sk_gym_service.service.api;

import raf.sk.sk_gym_service.dto.GymTrainingTypeDto;

public interface GymTrainingTypeServiceApi {

    GymTrainingTypeDto findByGymIdAndTrainingTypeId(Long gymId, Long trainingTypeId);


    GymTrainingTypeDto createGymTrainingType(Long gymId, Long trainingTypeId, Double price);
}
