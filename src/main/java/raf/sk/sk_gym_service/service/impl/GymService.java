package raf.sk.sk_gym_service.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raf.sk.sk_gym_service.dto.GymDto;
import raf.sk.sk_gym_service.entity_model.Gym;
import raf.sk.sk_gym_service.object_mapper.ObjectMapper;
import raf.sk.sk_gym_service.repository.GymRepository;
import raf.sk.sk_gym_service.service.api.GymServiceApi;

import java.util.List;
import java.util.Optional;


@Service
public class GymService implements GymServiceApi {

    private final GymRepository gymRepository;

    public GymService(GymRepository gymRepository) {
        this.gymRepository = gymRepository;
    }

    @Override
    public List<GymDto> getAllGyms() {
        return gymRepository.findAllByDeletedFalse().stream()
                .map(ObjectMapper::gymToDto).toList();
    }

    @Override
    public GymDto getGymById(Long gymId) {
        return gymRepository.findByIdAndDeletedFalse(gymId).map(ObjectMapper::gymToDto).orElse(null);
    }

    @Override
    public GymDto createGym(GymDto gymDto) {
        Gym gym = new Gym();
        gym.setDeleted(false);
        gym.setName(gymDto.getName());
        gym.setManagerEmail(gymDto.getManagerEmail());
        gym.setNumberOfTrainers(gymDto.getNumberOfTrainers());
        return ObjectMapper.gymToDto(gymRepository.save(gym));
    }

    @Override
    public GymDto updateGym(Long gymId, GymDto updatedGym) {
        Optional<Gym> existingGymOptional = gymRepository.findByIdAndDeletedFalse(gymId);
        if (existingGymOptional.isEmpty())
            return null;
        Gym existingGym = existingGymOptional.get();
        existingGym.setName(updatedGym.getName());
        existingGym.setNumberOfTrainers(updatedGym.getNumberOfTrainers());
        existingGym.setManagerEmail(updatedGym.getManagerEmail());
        return ObjectMapper.gymToDto(gymRepository.save(existingGym));
    }

    @Override
    @Transactional
    public boolean deleteGym(Long gymId) {
        Optional<Gym> gymOptional = gymRepository.findByIdAndDeletedFalse(gymId);
        if (gymOptional.isPresent()) {
            Gym gym = gymOptional.get();
            gym.setDeleted(true);
            return true;
        }
        return false; // Gym not found
    }

}
