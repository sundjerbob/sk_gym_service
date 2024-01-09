package raf.sk.sk_gym_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raf.sk.sk_gym_service.entity_model.TrainingType;
import raf.sk.sk_gym_service.repository.TrainingTypeRepository;

import java.util.List;

@RestController
@RequestMapping("/api/training-types")
public class TrainingTypeController {

    private final TrainingTypeRepository trainingTypeRepository;

    public TrainingTypeController(TrainingTypeRepository trainingTypeRepository) {
        this.trainingTypeRepository = trainingTypeRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainingType> getTrainingTypeById(@PathVariable Long id) {
        return trainingTypeRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<TrainingType>> getAllTrainingTypes() {
        List<TrainingType> trainingTypes = trainingTypeRepository.findAll();
        return ResponseEntity.ok(trainingTypes);
    }

    @PostMapping
    public ResponseEntity<TrainingType> createTrainingType(@RequestBody TrainingType trainingType) {
        TrainingType savedTrainingType = trainingTypeRepository.save(trainingType);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTrainingType);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrainingType> updateTrainingType(@PathVariable Long id, @RequestBody TrainingType updatedTrainingType) {
        return trainingTypeRepository.findById(id)
                .map(existingTrainingType -> {
                    existingTrainingType.setName(updatedTrainingType.getName());
                    existingTrainingType.setDescription(updatedTrainingType.getDescription());
                    // Update other fields as needed
                    return ResponseEntity.ok(trainingTypeRepository.save(existingTrainingType));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrainingType(@PathVariable Long id) {
        if (trainingTypeRepository.existsById(id)) {
            trainingTypeRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

