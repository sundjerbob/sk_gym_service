package raf.sk.sk_gym_service.service.api;

import raf.sk.sk_gym_service.dto.TrainingTypeDto;

import java.util.List;

public interface TrainingTypeServiceApi {

    TrainingTypeDto getTrainingTypeById(Long id);

    List<TrainingTypeDto> getAllTrainingTypes();

    TrainingTypeDto createTrainingType(TrainingTypeDto trainingType);

    TrainingTypeDto updateTrainingType(Long id, TrainingTypeDto trainingType);

    boolean deleteTrainingType(Long id);
}
