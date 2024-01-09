package raf.sk.sk_gym_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raf.sk.sk_gym_service.entity_model.BookedWorkout;
import raf.sk.sk_gym_service.repository.BookedWorkoutRepository;

@RestController
@RequestMapping("/api/booked-workouts")
public class BookedWorkoutController {

    private final BookedWorkoutRepository bookedWorkoutRepository;

    public BookedWorkoutController(BookedWorkoutRepository bookedWorkoutRepository) {
        this.bookedWorkoutRepository = bookedWorkoutRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookedWorkout> getBookedWorkoutById(@PathVariable Long id) {
        return bookedWorkoutRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<BookedWorkout> createBookedWorkout(@RequestBody BookedWorkout bookedWorkout) {
        BookedWorkout savedWorkout = bookedWorkoutRepository.save(bookedWorkout);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedWorkout);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookedWorkout> updateBookedWorkout(@PathVariable Long id, @RequestBody BookedWorkout updatedWorkout) {
        return bookedWorkoutRepository.findById(id)
                .map(existingWorkout -> {
                    existingWorkout.setUserEmail(updatedWorkout.getUserEmail());
                    // Update other fields as needed
                    return ResponseEntity.ok(bookedWorkoutRepository.save(existingWorkout));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookedWorkout(@PathVariable Long id) {
        if (bookedWorkoutRepository.existsById(id)) {
            bookedWorkoutRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

