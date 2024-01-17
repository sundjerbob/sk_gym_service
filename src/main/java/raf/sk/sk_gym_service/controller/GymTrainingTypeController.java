package raf.sk.sk_gym_service.controller;

import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import raf.sk.sk_gym_service.dto.GymTrainingTypeDto;
import raf.sk.sk_gym_service.service.api.GymTrainingTypeServiceApi;

@RestController
@RequestMapping("/gym-training-types")
public class GymTrainingTypeController {

    private final GymTrainingTypeServiceApi trainingTypeService;

    public GymTrainingTypeController(GymTrainingTypeServiceApi trainingTypeService) {
        this.trainingTypeService = trainingTypeService;
    }

    @PostMapping
    public ResponseEntity<GymTrainingTypeDto> createGymTrainingType(@PathParam("gymId") Long gymId,
                                                                    @PathParam("trainingTypeId") Long trainingTypeId,
                                                                    @PathParam("price") Double price) {

        GymTrainingTypeDto gymTrainingTypeDto = trainingTypeService.createGymTrainingType(gymId, trainingTypeId, price);
        return gymTrainingTypeDto != null ? ResponseEntity.ok(gymTrainingTypeDto) : ResponseEntity.badRequest().build();
    }
}
