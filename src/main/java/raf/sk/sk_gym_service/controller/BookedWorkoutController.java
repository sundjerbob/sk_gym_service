package raf.sk.sk_gym_service.controller;

import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import raf.sk.sk_gym_service.dto.BookedWorkoutDto;
import raf.sk.sk_gym_service.service.api.BookedWorkoutServiceApi;

@RestController
@RequestMapping("/booked-workouts")
public class BookedWorkoutController {

    private final BookedWorkoutServiceApi bookedWorkoutService;

    public BookedWorkoutController(BookedWorkoutServiceApi bookedWorkoutService) {
        this.bookedWorkoutService = bookedWorkoutService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookedWorkoutDto> getBookedWorkoutById(@PathVariable Long id) {
        BookedWorkoutDto bookedWorkout = bookedWorkoutService.getBookedWorkoutById(id);
        return bookedWorkout != null ? ResponseEntity.ok(bookedWorkout) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Transactional
    @PostMapping("bookScheduledWorkout/{scheduleWorkoutId}")
    public ResponseEntity<BookedWorkoutDto> createBookedWorkout(@PathVariable("scheduleWorkoutId") Long scheduleWorkoutId,
                                                                @RequestBody String userEmail,
                                                                @RequestHeader(name = "Authorization") String authorization) {
        BookedWorkoutDto bookedWorkoutDto = bookedWorkoutService.createBookedWorkout(
                scheduleWorkoutId, authorization);
        return bookedWorkoutDto != null ? ResponseEntity.ok(bookedWorkoutDto) : ResponseEntity.badRequest().build();
    }



}

