package raf.sk.sk_gym_service.service.impl;

import org.springframework.stereotype.Service;
import raf.sk.sk_gym_service.dto.model.BookedWorkoutDto;
import raf.sk.sk_gym_service.object_mapper.ObjectMapper;
import raf.sk.sk_gym_service.repository.BookedWorkoutRepository;
import raf.sk.sk_gym_service.service.api.BookedWorkoutServiceApi;

@Service
public class BookedWorkoutService implements BookedWorkoutServiceApi {

    private BookedWorkoutRepository bookedWorkoutRepository;

    public BookedWorkoutService(BookedWorkoutRepository bookedWorkoutRepository) {
        this.bookedWorkoutRepository = bookedWorkoutRepository;
    }

    public BookedWorkoutDto getBookedWorkoutById(Long id) {
        return bookedWorkoutRepository.findById(id)
                .map(ObjectMapper::bookedWorkoutToDto)
                .orElse(null);
    }

    @Override
    public BookedWorkoutDto createBookedWorkout(BookedWorkoutDto bookedWorkoutDto) {
        return ObjectMapper.bookedWorkoutToDto(
                bookedWorkoutRepository.save(
                        ObjectMapper.dtoToBookedWorkout(
                                bookedWorkoutDto
                        )
                )
        );
    }

    @Override
    public BookedWorkoutDto updateBookedWorkout(Long id, BookedWorkoutDto updatedWorkoutDto) {
        return bookedWorkoutRepository.findById(id)
                .map(bookedWorkout -> {
                            bookedWorkout.setUserEmail(updatedWorkoutDto.getUserEmail());
                            bookedWorkout.setScheduledWorkout(ObjectMapper.dtoToScheduledWorkout(updatedWorkoutDto.getScheduledWorkout()));
                            return ObjectMapper.bookedWorkoutToDto(bookedWorkoutRepository.save(bookedWorkout));
                        }
                )
                .orElse(null);
    }

    @Override
    public boolean setCanceledTo(Long id, Boolean setCanceledTo) {
        return bookedWorkoutRepository.setCanceledTo(id, setCanceledTo) == 1;
    }


}
