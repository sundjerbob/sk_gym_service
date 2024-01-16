package raf.sk.sk_gym_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raf.sk.sk_gym_service.dto.model.TrainingTypeDto;
import raf.sk.sk_gym_service.repository.TrainingTypeRepository;
import raf.sk.sk_gym_service.service.api.TrainingTypeServiceApi;

import java.util.List;

@RestController
@RequestMapping("/api/training-types")
public class TrainingTypeController {

    private final TrainingTypeRepository trainingTypeRepository;
    private final TrainingTypeServiceApi trainingTypeService;

    public TrainingTypeController(TrainingTypeRepository trainingTypeRepository, TrainingTypeServiceApi trainingTypeService) {
        this.trainingTypeRepository = trainingTypeRepository;
        this.trainingTypeService = trainingTypeService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainingTypeDto> getTrainingTypeById(@PathVariable Long id) {
        TrainingTypeDto trainingType = trainingTypeService.getTrainingTypeById(id);
        return trainingType != null ? ResponseEntity.ok(trainingType) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<TrainingTypeDto>> getAllTrainingTypes() {
        return ResponseEntity.ok(trainingTypeService.getAllTrainingTypes());
    }

    @PostMapping
    public ResponseEntity<TrainingTypeDto> createTrainingType(@RequestBody TrainingTypeDto trainingType) {
        return new ResponseEntity<>(
                trainingTypeService.createTrainingType(trainingType),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrainingTypeDto> updateTrainingType(@PathVariable Long id, @RequestBody TrainingTypeDto updatedTrainingType) {
        TrainingTypeDto trainingType = trainingTypeService.updateTrainingType(id, updatedTrainingType);
        return trainingType != null ? ResponseEntity.ok(trainingType) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrainingType(@PathVariable Long id) {
        return trainingTypeService.deleteTrainingType(id) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}

