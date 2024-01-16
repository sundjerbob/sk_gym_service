package raf.sk.sk_gym_service.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raf.sk.sk_gym_service.dto.model.TrainingTypeDto;
import raf.sk.sk_gym_service.entity_model.TrainingType;
import raf.sk.sk_gym_service.object_mapper.ObjectMapper;
import raf.sk.sk_gym_service.repository.TrainingTypeRepository;
import raf.sk.sk_gym_service.service.api.TrainingTypeServiceApi;

import java.util.List;

@Service
public class TrainingTypeService implements TrainingTypeServiceApi {
    private final TrainingTypeRepository trainingTypeRepository;


    public TrainingTypeService(TrainingTypeRepository trainingTypeRepository) {
        this.trainingTypeRepository = trainingTypeRepository;
    }

    @Override
    public TrainingTypeDto getTrainingTypeById(Long id) {
        return trainingTypeRepository.findById(id).map(
                ObjectMapper::trainingTypeToDto
        ).orElse(null);
    }

    @Override
    public List<TrainingTypeDto> getAllTrainingTypes() {
        return trainingTypeRepository.findAll().stream().map(
                ObjectMapper::trainingTypeToDto
        ).toList();
    }

    @Override
    public TrainingTypeDto createTrainingType(TrainingTypeDto trainingTypeDto) {
        TrainingType newTrainingType = new TrainingType();
        newTrainingType.setName(trainingTypeDto.getName());
        newTrainingType.setDescription(trainingTypeDto.getDescription());
        newTrainingType.setIndividual(trainingTypeDto.isIndividual());
        newTrainingType.setDurationInMinutes(trainingTypeDto.getDurationInMinutes());
        newTrainingType.setMaxParticipants(trainingTypeDto.getMaxParticipants());
        return ObjectMapper.trainingTypeToDto(trainingTypeRepository.save(newTrainingType));
    }

    @Transactional
    @Override
    public TrainingTypeDto updateTrainingType(Long id, TrainingTypeDto trainingTypeDto) {
        if (trainingTypeDto.getId().equals(id))
            throw new RuntimeException("createTrainingType; ids doesn't match... x,X");
        return trainingTypeRepository.findById(id).map(
                trainingType -> {
                    trainingType.setName(trainingTypeDto.getName());
                    trainingType.setDescription(trainingTypeDto.getDescription());
                    trainingType.setDurationInMinutes(trainingTypeDto.getDurationInMinutes());
                    trainingType.setMaxParticipants(trainingTypeDto.getMaxParticipants());
                    trainingType.setIndividual(trainingTypeDto.isIndividual());
                    return ObjectMapper.trainingTypeToDto(trainingTypeRepository.save(trainingType));
                }
        ).orElse(null);
    }


    @Transactional
    @Override
    public boolean deleteTrainingType(Long id) {
        if (trainingTypeRepository.existsById(id)) {
            trainingTypeRepository.deleteById(id);
            return true;
        }
        return false;

    }
}
