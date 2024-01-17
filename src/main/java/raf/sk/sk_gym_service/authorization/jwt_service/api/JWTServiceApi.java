package raf.sk.sk_gym_service.authorization.jwt_service.api;

import raf.sk.sk_gym_service.authorization.jwt_service.dto.UnpackedAuthToken;


public interface JWTServiceApi {




    UnpackedAuthToken unpackClaimsInfo(String jwtAuthToken);


}
