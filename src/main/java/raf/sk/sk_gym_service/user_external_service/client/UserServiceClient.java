package raf.sk.sk_gym_service.user_external_service.client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import raf.sk.sk_gym_service.user_external_service.inter_service_comunication.UserPerksDto;

@Component
public class UserServiceClient {

    private final RestTemplate userServiceRestTemplate;
    private static final int MAX_RETRIES = 3;
    private static final long WAIT_TIME_MS = 1000; // 1 second

    public UserServiceClient(RestTemplate userServiceRestTemplate) {
        this.userServiceRestTemplate = userServiceRestTemplate;
    }

    public UserPerksDto getUserPerksWithRetry(String authorizationHeader, Long userId, String gymName) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorizationHeader);

        String url = "/clients/getPerks?userId=" + userId + "&gymName=" + gymName;

        HttpEntity<String> entity = new HttpEntity<>(headers);

        int retries = 0;
        ResponseEntity<UserPerksDto> responseEntity = null;

        while (retries < MAX_RETRIES) {
            try {
                responseEntity = userServiceRestTemplate.exchange(
                        url,
                        HttpMethod.GET,
                        entity,
                        UserPerksDto.class
                );
                if (responseEntity.getStatusCode().is2xxSuccessful()) {
                    return responseEntity.getBody();
                }
            } catch (Exception e) {
                // Log or handle the exception
            }

            // Introduce a wait time between retries
            try {
                Thread.sleep(WAIT_TIME_MS);
            } catch (InterruptedException ignored) {
                Thread.currentThread().interrupt();
            }

            retries++;
        }

        // Handle the case when retries are exhausted or the response is not successful
        return null;
    }
}
