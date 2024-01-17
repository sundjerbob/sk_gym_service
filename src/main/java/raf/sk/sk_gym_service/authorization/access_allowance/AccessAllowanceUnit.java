package raf.sk.sk_gym_service.authorization.access_allowance;


import org.springframework.stereotype.Component;
import raf.sk.sk_gym_service.authorization.jwt_service.api.JWTServiceApi;
import raf.sk.sk_gym_service.authorization.jwt_service.dto.UnpackedAuthToken;
import raf.sk.sk_gym_service.authorization.perm.Permissions;
import raf.sk.sk_gym_service.repository.GymRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static raf.sk.sk_gym_service.authorization.perm.Permissions.*;


@Component
public class AccessAllowanceUnit {


    private final JWTServiceApi jwtServiceApi;

    private final Map<Permissions, PermitLambda> permissionChecks;

    private final GymRepository gymRepository;


    public AccessAllowanceUnit(JWTServiceApi jwtServiceApi, GymRepository gymRepository) {
        this.jwtServiceApi = jwtServiceApi;
        this.gymRepository = gymRepository;

        permissionChecks = new HashMap<>();

        permissionChecks.put(
                ALL_USER_DATA_ACCESS,
                (claims, requestedRecordId) ->
                        claims.getRequesterRole().getPermissions().contains(ALL_USER_DATA_ACCESS));

        permissionChecks.put(
                PERSONAL_USER_DATA_ACCESS,
                (claims, requestedRecordId) ->
                        claims.getRequesterRole().getPermissions().contains(PERSONAL_USER_DATA_ACCESS)
                                && requestedRecordId != null
                                && requestedRecordId.equals(claims.getRequesterId())
        );
        permissionChecks.put(
                GYM_DATA_EDIT,
                (claims, requestedRecordId) ->
                        claims.getRequesterRole().getPermissions().contains(GYM_DATA_EDIT) && (
                                requestedRecordId == null || (
                                        gymRepository.existsById(requestedRecordId)
                                                && gymRepository.getReferenceById(requestedRecordId).
                                                getManagerEmail().equals(claims.getRequesterEmail())
                                )
                        )
        );


    }


    public boolean allowAction(String requesterClaimsToken, Long requestedRecordId, List<Permissions> requiredPermissions) {

        UnpackedAuthToken requesterClaims = jwtServiceApi.unpackClaimsInfo(requesterClaimsToken);
        System.out.println(requestedRecordId);

        System.out.println(requesterClaims);

        for (Permissions reqPerm : requiredPermissions) {
            System.out.println("Permission: " + reqPerm + " acquire check for requester: " + requesterClaims.getRequesterUsername());
            if (permissionChecks.get(reqPerm).grantAccess(requesterClaims, requestedRecordId)) {
                System.out.println("Requester: " + requesterClaims.getRequesterUsername() + " has permission: " + reqPerm + ".\nAccess is granted.");
                return true;
            }
            System.out.println("Requester: " + requesterClaims.getRequesterUsername() + " does not acquire permission: " + reqPerm + ".");
        }

        // if none of the requiredPermission checks had granted access the action is not allowed
        return false;
    }


    private interface PermitLambda {
        boolean grantAccess(UnpackedAuthToken claims, Long requestedRecordId);
    }


}
