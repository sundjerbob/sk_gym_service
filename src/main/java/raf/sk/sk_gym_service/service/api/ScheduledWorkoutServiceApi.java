package raf.sk.sk_gym_service.service.api;

import raf.sk.sk_gym_service.dto.ScheduledWorkoutDto;

import java.util.List;

public interface ScheduledWorkoutServiceApi {
    ScheduledWorkoutDto getScheduledWorkoutById(Long id);

    List<ScheduledWorkoutDto> getScheduledWorkoutForGym(Long gymId);

    ScheduledWorkoutDto createScheduledWorkout(ScheduledWorkoutDto scheduledWorkoutDto);


    ScheduledWorkoutDto updateScheduledWorkout(Long id, ScheduledWorkoutDto updatedScheduledWorkout);
    boolean deleteWorkoutSchedule(Long id);

}
