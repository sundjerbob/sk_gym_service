package raf.sk.sk_gym_service.service.api;

import raf.sk.sk_gym_service.dto.GymDto;

import java.util.List;

public interface GymServiceApi {

    List<GymDto> getAllGyms();

    GymDto getGymById(Long gymId);

    GymDto createGym(GymDto gym);

    GymDto updateGym(Long gymId, GymDto updatedGym);

    boolean deleteGym(Long gymId);
}
