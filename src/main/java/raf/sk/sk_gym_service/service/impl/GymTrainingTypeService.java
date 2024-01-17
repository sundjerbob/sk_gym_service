package raf.sk.sk_gym_service.service.impl;

import org.springframework.stereotype.Service;
import raf.sk.sk_gym_service.dto.GymTrainingTypeDto;
import raf.sk.sk_gym_service.entity_model.Gym;
import raf.sk.sk_gym_service.entity_model.GymTrainingType;
import raf.sk.sk_gym_service.entity_model.TrainingType;
import raf.sk.sk_gym_service.object_mapper.ObjectMapper;
import raf.sk.sk_gym_service.repository.GymRepository;
import raf.sk.sk_gym_service.repository.GymTrainingTypeRepository;
import raf.sk.sk_gym_service.repository.TrainingTypeRepository;
import raf.sk.sk_gym_service.service.api.GymTrainingTypeServiceApi;

import java.util.Optional;


@Service
public class GymTrainingTypeService implements GymTrainingTypeServiceApi {

    private final GymTrainingTypeRepository gymTrainingTypeRepository;
    private final TrainingTypeRepository trainingTypeRepository;

    private final GymRepository gymRepository;

    public GymTrainingTypeService(GymTrainingTypeRepository gymTrainingTypeRepository, TrainingTypeRepository trainingTypeRepository, GymRepository gymRepository) {
        this.gymTrainingTypeRepository = gymTrainingTypeRepository;
        this.trainingTypeRepository = trainingTypeRepository;
        this.gymRepository = gymRepository;
    }

    @Override
    public GymTrainingTypeDto findByGymIdAndTrainingTypeId(Long gymId, Long trainingTypeId) {
        return gymTrainingTypeRepository.findByGymIdAndTrainingTypeId(gymId, trainingTypeId)
                .map(ObjectMapper::gymTrainingTypeToDto
                ).orElse(null);
    }

    @Override
    public GymTrainingTypeDto createGymTrainingType(Long gymId, Long trainingTypeId, Double price) {

        Optional<Gym> gymOptional = gymRepository.findById(gymId);
        if (gymOptional.isEmpty())
            return null;

        Optional<TrainingType> trainingTypeOptional = trainingTypeRepository.findById(trainingTypeId);
        if (trainingTypeOptional.isEmpty())
            return null;

        GymTrainingType gymTrainingType = new GymTrainingType();
        gymTrainingType.setGym(gymOptional.get());
        gymTrainingType.setTrainingType(trainingTypeOptional.get());
        gymTrainingType.setPrice(price);
        return ObjectMapper.gymTrainingTypeToDto(gymTrainingTypeRepository.save(gymTrainingType));
    }


}
