package raf.sk.sk_gym_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raf.sk.sk_gym_service.authorization.anotation.Authorization;
import raf.sk.sk_gym_service.authorization.anotation.RequestedRecordIdentifier;
import raf.sk.sk_gym_service.authorization.jwt_service.api.JWTServiceApi;
import raf.sk.sk_gym_service.authorization.jwt_service.dto.UnpackedAuthToken;
import raf.sk.sk_gym_service.authorization.roles.Role;
import raf.sk.sk_gym_service.dto.GymDto;
import raf.sk.sk_gym_service.service.api.GymServiceApi;

import java.util.List;

@RestController
@RequestMapping("/gyms")

public class GymController {
    private final GymServiceApi gymService;

    private final JWTServiceApi jwtService;

    public GymController(GymServiceApi gymService, JWTServiceApi jwtService) {
        this.gymService = gymService;
        this.jwtService = jwtService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<GymDto> getGymById(@PathVariable(name = "id") Long id) {
        GymDto gym = gymService.getGymById(id);
        return gym != null ? ResponseEntity.ok(gym) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<GymDto>> getAllGyms() {
        return ResponseEntity.ok(gymService.getAllGyms());
    }

    @Authorization(
            requiredPermissions = {"ALL_USER_DATA_ACCESS", "GYM_DATA_EDIT"},
            authTokenArgName = "authorization"
    )
    @PostMapping
    public ResponseEntity<GymDto> createGym(GymDto gymDto, @RequestHeader("Authorization") String authorization) {
        UnpackedAuthToken requesterInfo = jwtService.unpackClaimsInfo(authorization.split(" +")[1]);

        if (requesterInfo.getRequesterRole() != Role.ADMIN && !gymDto.getManagerEmail().equals(requesterInfo.getRequesterEmail()))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        GymDto createdGym = gymService.createGym(gymDto);

        return createdGym != null ? ResponseEntity.ok(createdGym) : ResponseEntity.notFound().build();
    }

    @Authorization(
            requiredPermissions = {"ALL_USER_DATA_ACCESS", "GYM_DATA_EDIT"},
            authTokenArgName = "authorization"
    )
    @RequestedRecordIdentifier(argName = "id")
    @PutMapping("/{id}")
    public ResponseEntity<GymDto> editGym(@PathVariable Long id, GymDto newData) {
        GymDto gymDto = gymService.updateGym(id, newData);
        return gymDto != null ? ResponseEntity.ok(gymDto) : ResponseEntity.notFound().build();
    }

    @Authorization(
            requiredPermissions = {"ALL_USER_DATA_ACCESS", "GYM_DATA_EDIT"},
            authTokenArgName = "authorization"
    )
    @RequestedRecordIdentifier(argName = "id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGym(@PathVariable Long id) {
        return gymService.deleteGym(id) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

}
