package raf.sk.sk_gym_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raf.sk.sk_gym_service.dto.model.ScheduledWorkoutDto;
import raf.sk.sk_gym_service.entity_model.ScheduledWorkout;
import raf.sk.sk_gym_service.repository.WorkoutScheduleRepository;

import java.util.List;

@RestController
@RequestMapping("/api/workout-schedules")
public class ScheduledWorkoutController {

    private final WorkoutScheduleRepository workoutScheduleRepository;

    public ScheduledWorkoutController(WorkoutScheduleRepository workoutScheduleRepository) {
        this.workoutScheduleRepository = workoutScheduleRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduledWorkout> getWorkoutScheduleById(@PathVariable Long id) {
        return workoutScheduleRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ScheduledWorkout>> getAllWorkoutSchedules() {
        List<ScheduledWorkout> scheduledWorkouts = workoutScheduleRepository.findAll();
        return ResponseEntity.ok(scheduledWorkouts);
    }

    @PostMapping
    public ResponseEntity<ScheduledWorkout> createWorkoutSchedule(@RequestBody ScheduledWorkout scheduledWorkout) {
        ScheduledWorkout savedScheduledWorkout = workoutScheduleRepository.save(scheduledWorkout);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedScheduledWorkout);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ScheduledWorkout> updateWorkoutSchedule(@PathVariable Long id, @RequestBody ScheduledWorkoutDto updatedScheduledWorkout) {
        return workoutScheduleRepository.findById(id)
                .map(existingScheduledWorkout -> {
                    // Update fields based on your requirements
                    existingScheduledWorkout.setTrainingType(updatedScheduledWorkout.getTrainingTypeName());
                    existingScheduledWorkout.setStartTime(updatedScheduledWorkout.getStartTime());
                    return ResponseEntity.ok(workoutScheduleRepository.save(existingScheduledWorkout));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkoutSchedule(@PathVariable Long id) {
        if (workoutScheduleRepository.existsById(id)) {
            workoutScheduleRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

