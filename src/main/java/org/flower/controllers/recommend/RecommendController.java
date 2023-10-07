package org.flower.controllers.recommend;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/recommend")
public class RecommendController {
    @GetMapping
    public String recommend(Model model) {
        // 키워드 추가하기 위한 부분
        // 나중에 이 부분은 model에서 따로 받아와야함 -대윤-
        List<String> keywords = Arrays.asList("졸업식", "연인", "부모님");

        // 키워드를 모델에 추가하여 뷰에서 사용
        model.addAttribute("keywords", keywords);
        model.addAttribute("pageTitle", "꽃 추천");

        return "front/recommend/main";
    }

}
