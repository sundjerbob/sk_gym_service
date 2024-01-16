package raf.sk.sk_gym_service.service.api;


import raf.sk.sk_gym_service.dto.model.BookedWorkoutDto;

public interface BookedWorkoutServiceApi {
    BookedWorkoutDto getBookedWorkoutById(Long id);

    BookedWorkoutDto createBookedWorkout(BookedWorkoutDto bookedWorkout);

    BookedWorkoutDto updateBookedWorkout(Long id, BookedWorkoutDto updatedWorkout);

    boolean setCanceledTo(Long id, Boolean setCanceledTo);
}
