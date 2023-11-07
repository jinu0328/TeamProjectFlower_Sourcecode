package org.flower.controllers.postCard;

import org.flower.models.postCard.OpenAIService;
import org.flower.models.postCard.OpenAiInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/postCard")
public class OpenAIController {

    private final OpenAIService openAIService;

    @Autowired
    public OpenAIController(OpenAIService openAIService) {
        this.openAIService = openAIService;
    }

    @GetMapping("/create-image")
    public String showCreateImageForm(Model model) {
        model.addAttribute("request", new OpenAiInfo()); // ImageRequest는 prompt 필드를 갖는 폼 오브젝트입니다.
        return "front/postcard/main"; // create-image.html 뷰로 맵핑됩니다.
    }

    @PostMapping("/create-image")
    public String createImage(@RequestParam("prompt") String prompt, Model model) {
        ResponseEntity<Map<String, Object>> response = openAIService.createImage(prompt);
        model.addAttribute("imageData", response.getBody().get("data"));
        System.out.println(response.getBody().get("data"));
        return "front/postcard/result"; // result.html로 반환됩니다.
    }


}
