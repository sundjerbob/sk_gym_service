package raf.sk.sk_gym_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import raf.sk.sk_gym_service.dto.model.TrainingTypeDto;
import raf.sk.sk_gym_service.object_mapper.ObjectMapper;
import raf.sk.sk_gym_service.repository.TrainingTypeRepository;

@Service
public class GymController {
    private final TrainingTypeRepository trainingTypeRepository;

    public GymController(TrainingTypeRepository trainingTypeRepository) {
        this.trainingTypeRepository = trainingTypeRepository;
    }

    public TrainingTypeDto getTrainingTypeById(Long id) {
        return trainingTypeRepository.findById(id)
                .map(ObjectMapper::trainingTypeToDto)
                .orElse(null);
    }

}
