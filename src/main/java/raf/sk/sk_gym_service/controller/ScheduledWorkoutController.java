package raf.sk.sk_gym_service.controller;

import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import raf.sk.sk_gym_service.dto.ScheduledWorkoutDto;
import raf.sk.sk_gym_service.service.api.ScheduledWorkoutServiceApi;

import java.util.List;

@RestController
@RequestMapping("/scheduled-workouts")
public class ScheduledWorkoutController {

    private final ScheduledWorkoutServiceApi scheduledWorkoutService;

    public ScheduledWorkoutController(ScheduledWorkoutServiceApi scheduledWorkoutService) {
        this.scheduledWorkoutService = scheduledWorkoutService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduledWorkoutDto> getScheduledWorkoutById(@PathVariable Long id) {
        ScheduledWorkoutDto scheduledWorkoutDto = scheduledWorkoutService.getScheduledWorkoutById(id);

        return scheduledWorkoutDto != null ? ResponseEntity.ok(scheduledWorkoutDto) : ResponseEntity.notFound().build();

    }

    @GetMapping
    public ResponseEntity<List<ScheduledWorkoutDto>> getScheduledWorkoutsForGym(Long gymId) {
        return ResponseEntity.ok(
                scheduledWorkoutService.getScheduledWorkoutForGym(gymId)
        );
    }

    @PostMapping
    public ResponseEntity<ScheduledWorkoutDto> createWorkoutSchedule(@RequestBody ScheduledWorkoutDto scheduledWorkout) {
        ScheduledWorkoutDto workoutDto = scheduledWorkoutService.createScheduledWorkout(scheduledWorkout);
        return workoutDto != null ? ResponseEntity.ok(workoutDto) : ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ScheduledWorkoutDto> updateScheduledWorkout(@PathVariable Long id, @RequestBody ScheduledWorkoutDto updatedScheduledWorkout) {
        ScheduledWorkoutDto workoutDto = scheduledWorkoutService.updateScheduledWorkout(id, updatedScheduledWorkout);
        return workoutDto != null ? ResponseEntity.ok(workoutDto) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteScheduledWorkout(@PathVariable Long id) {
        return scheduledWorkoutService.deleteWorkoutSchedule(id) ?
                ResponseEntity.ok().build() : ResponseEntity.notFound().build();

    }

    @Transactional
    @PutMapping("/cancel")
    public ResponseEntity<Void> setCanceledTo(@PathParam("id") Long id, @PathParam("isCanceled") Boolean isCanceled) {
        return scheduledWorkoutService.setCanceledTo(id, isCanceled) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }


}

