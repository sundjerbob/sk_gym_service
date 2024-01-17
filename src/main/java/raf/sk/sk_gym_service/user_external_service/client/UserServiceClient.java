package raf.sk.sk_gym_service.user_external_service.client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import raf.sk.sk_gym_service.user_external_service.inter_service_comunication.UserPerks;

@Component
public class UserServiceClient {

    private final RestTemplate userServiceRestTemplate;

    public UserServiceClient(RestTemplate userServiceRestTemplate) {
        this.userServiceRestTemplate = userServiceRestTemplate;
    }


    public UserPerks getUserPerks(String authorizationHeader, Long userId, String gymName) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorizationHeader);

        String url = "/clients/getPerks?userId=" + userId + "&gymName=" + gymName;

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<UserPerks> responseEntity = userServiceRestTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                UserPerks.class
        );

        return responseEntity.getBody();
    }
}
