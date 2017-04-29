package xyz.sleepiizcatz.docupool.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class AuthenticationService {

    @Value("${api.user-info}")
    private String userInfoAPI;

    public Map<String, Object> getIdentity(Map<String, String> credentials) {

        Map<String, Object> user = null;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(credentials, headers);

        RestTemplate restTemplate = new RestTemplate();
        try {
            user = restTemplate.postForObject(userInfoAPI, request, Map.class);
        } catch (Exception e) { } // User not found

        return user;
    }
}
