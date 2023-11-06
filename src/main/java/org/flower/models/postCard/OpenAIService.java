package org.flower.models.postCard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OpenAIService {

    private final RestTemplate restTemplate;
    private final String openAIUrl = "https://api.openai.com/v1/images/generations"; // DALL·E API URL
    private final String apiKey = "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"; // 실제 API 키로 대체해야 합니다.

    @Autowired
    public OpenAIService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String generateImage(String prompt) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        String requestBody = "{\"prompt\": \"" + prompt + "\", \"n\": 1}"; // n은 생성할 이미지 수입니다.

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        return restTemplate.postForObject(openAIUrl, entity, String.class);
    }
}
