package org.flower.controllers.postCard;

import org.flower.models.postCard.OpenAIService;
import org.flower.models.postCard.OpenAiInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
        List<Map<String, Object>> dataList = (List<Map<String, Object>>) response.getBody().get("data");
        String imageUrl = "";
        if (dataList != null && !dataList.isEmpty()) {
            // 첫 번째 아이템의 맵에서 'url' 키에 해당하는 값을 가져옵니다.
            imageUrl = (String) dataList.get(0).get("url");
            // imageUrl 변수를 사용합니다. 예를 들어 콘솔에 출력하거나 모델에 추가할 수 있습니다.
            System.out.println(imageUrl);
        }
       model.addAttribute("imageData", imageUrl);
//        System.out.println(response.getBody().get("data"));
        return "front/postcard/result"; // result.html로 반환됩니다.
    }


}
