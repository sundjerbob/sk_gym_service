package raf.sk.sk_gym_service.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import raf.sk.sk_gym_service.dto.GymDto;
import raf.sk.sk_gym_service.dto.GymTrainingTypeDto;
import raf.sk.sk_gym_service.dto.TrainingTypeDto;
import raf.sk.sk_gym_service.service.api.GymServiceApi;
import raf.sk.sk_gym_service.service.api.GymTrainingTypeServiceApi;
import raf.sk.sk_gym_service.service.api.TrainingTypeServiceApi;

@Profile({"default"})
@Component
public class TestDataRunner implements CommandLineRunner {

    private final GymServiceApi gymService;
    private final TrainingTypeServiceApi trainingTypeService;

    private final GymTrainingTypeServiceApi gymTrainingTypeService;

    public TestDataRunner(GymServiceApi gymService, TrainingTypeServiceApi trainingTypeService, GymTrainingTypeServiceApi gymTrainingTypeService) {
        this.gymService = gymService;
        this.trainingTypeService = trainingTypeService;
        this.gymTrainingTypeService = gymTrainingTypeService;
    }

    @Override
    public void run(String... args) {
        GymDto gymDto = new GymDto();
        gymDto.setName("Fitness world");
        gymDto.setNumberOfTrainers(14);
        gymDto.setManagerEmail("mion@gmail.com");

        gymDto = gymService.createGym(gymDto);

        TrainingTypeDto trainingTypeDto = new TrainingTypeDto();
        trainingTypeDto.setName("Personalni box training sat vremena");
        trainingTypeDto.setDescription("Intermediate");
        trainingTypeDto.setIndividual(true);
        trainingTypeDto.setDurationInMinutes(60);
        trainingTypeDto.setMaxParticipants(1);
        trainingTypeDto = trainingTypeService.createTrainingType(trainingTypeDto);

        GymTrainingTypeDto gymTrainingTypeDto = gymTrainingTypeService.createGymTrainingType(
                gymDto.getId(),
                trainingTypeDto.getId(),
                20.0);

    }

}