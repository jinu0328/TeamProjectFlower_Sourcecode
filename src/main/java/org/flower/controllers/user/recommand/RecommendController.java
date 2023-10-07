package org.flower.controllers.user.recommand;

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
        List<String> keywords = Arrays.asList("졸업식", "연인", "부모님");
        model.addAttribute("keywords", keywords);
        return "front/recommend/main";
    }

}
