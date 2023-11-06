package org.flower.controllers.postCard;

import org.flower.models.postCard.OpenAIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/postCard")
public class OpenAIController {

    private final OpenAIService openAIService;

    @Autowired
    public OpenAIController(OpenAIService openAIService) {
        this.openAIService = openAIService;
    }

    @PostMapping("/create-image")
    public ResponseEntity<Map<String, Object>> createImage(@RequestBody String prompt) {
        // 반환 유형을 ResponseEntity<Map<String, Object>>로 수정합니다.
        return openAIService.createImage(prompt);
    }
}
