package org.flower.controllers.recommend;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class LoadingController {

    @PostMapping("/recommend/loading")
    public String loadingPage(@RequestParam String selectedKeywords, Model model) {
        // 컴마로 구분하여 키워드를 배열로 변환
        String[] keywords = selectedKeywords.split(",");

        // 키워드를 모델에 추가하여 뷰에서 사용
        model.addAttribute("selectedKeywords", keywords);
        model.addAttribute("pageTitle", "Loading");

        return "front/recommend/loading";
    }
}
