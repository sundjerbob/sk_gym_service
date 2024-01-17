package raf.sk.sk_gym_service.service.api;


import raf.sk.sk_gym_service.dto.BookedWorkoutDto;

import java.util.List;

public interface BookedWorkoutServiceApi {
    BookedWorkoutDto getBookedWorkoutById(Long id);


    List<BookedWorkoutDto> getBookedWorkoutByScheduledWorkout(Long scheduledWorkoutId);

    BookedWorkoutDto createBookedWorkout(Long scheduledWorkoutId, String authHeader);

    boolean setCanceledTo(Long id, Boolean setCanceledTo);
}
