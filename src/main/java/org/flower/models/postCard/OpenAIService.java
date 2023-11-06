package org.flower.models.postCard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class OpenAIService {

    private final RestTemplate restTemplate;
    private final String apiEndpoint = "https://api.openai.com/v1/images/generations";
    private final String apiKey;

    @Autowired
    public OpenAIService(RestTemplateBuilder restTemplateBuilder, @Value("${openai.api.key}") String apiKey) {
        this.restTemplate = restTemplateBuilder.build();
        this.apiKey = apiKey;
    }

    public ResponseEntity<Map<String, Object>> createImage(String prompt) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> requestPayload = Map.of(
                "prompt", prompt,
                "n", 1
        );

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestPayload, headers);

        try {
            // ResponseEntity를 Map<String, Object>로 명확히 반환하도록 수정
            return restTemplate.postForEntity(apiEndpoint, entity, (Class<Map<String, Object>>) (Class<?>) Map.class);
        } catch (RestClientException e) {
            throw new RestClientException("Error while calling DALL·E API: " + e.getMessage(), e);
        }
    }
}
