package raf.sk.sk_gym_service.controller;

import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import raf.sk.sk_gym_service.dto.model.BookedWorkoutDto;
import raf.sk.sk_gym_service.repository.BookedWorkoutRepository;
import raf.sk.sk_gym_service.service.api.BookedWorkoutServiceApi;

@RestController
@RequestMapping("booked-workouts")
public class BookedWorkoutController {

    private final BookedWorkoutServiceApi bookedWorkoutService;
    private final BookedWorkoutRepository bookedWorkoutRepository;

    public BookedWorkoutController(BookedWorkoutServiceApi bookedWorkoutService, BookedWorkoutRepository bookedWorkoutRepository) {
        this.bookedWorkoutService = bookedWorkoutService;
        this.bookedWorkoutRepository = bookedWorkoutRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookedWorkoutDto> getBookedWorkoutById(@PathVariable Long id) {
        BookedWorkoutDto bookedWorkout = bookedWorkoutService.getBookedWorkoutById(id);
        return bookedWorkout != null ? ResponseEntity.ok(bookedWorkout) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<BookedWorkoutDto> createBookedWorkout(@RequestBody BookedWorkoutDto bookedWorkoutDto) {
        return new ResponseEntity<>(bookedWorkoutService.createBookedWorkout(bookedWorkoutDto), HttpStatus.CREATED);
    }


    @Transactional
    @PutMapping("/edit/{id}")
    public ResponseEntity<BookedWorkoutDto> updateBookedWorkout(@PathVariable Long id, @RequestBody BookedWorkoutDto updatedWorkout) {
        BookedWorkoutDto bookedWorkoutDto = bookedWorkoutService.updateBookedWorkout(id, updatedWorkout);
        return bookedWorkoutDto != null ?
                ResponseEntity.ok(bookedWorkoutDto) : ResponseEntity.notFound().build();
    }

    @PutMapping("/cancel")
    public ResponseEntity<Void> setCanceledTo(@PathParam("id") Long id, @PathParam("isCanceled") Boolean isCanceled) {
        return bookedWorkoutService.setCanceledTo(id, isCanceled) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}

