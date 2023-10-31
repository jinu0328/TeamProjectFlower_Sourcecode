package org.flower.controllers.recommend;

import org.flower.entities.Keywords;
import org.flower.models.recommend.keyword.KeywordInfoService;
import org.flower.repositories.KeywordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/recommend")
public class RecommendController {
    @Autowired
    private KeywordInfoService keywordInfoService;

    @GetMapping
    public String recommend(Model model) {

        List<Keywords> keywordsList = keywordInfoService.getAllKeywords();

        // 키워드를 모델에 추가하여 뷰에서 사용
        model.addAttribute("pageTitle", "키워드 선택");
        model.addAttribute("keywordsList", keywordsList);

        return "front/recommend/main";
    }

    /* LoadingController에 있는 부분 가져옴 */
    /*
    * 추천 중 로딩 페이지 보여주는 역할
    * */
    @PostMapping("/loading")
    public String loadingPage(@RequestParam String selectedKeywords, Model model) {
        // 컴마로 구분하여 키워드를 배열로 변환
        String[] keywords = selectedKeywords.split(",");

        // 키워드를 모델에 추가하여 뷰에서 사용
        model.addAttribute("selectedKeywords", keywords);
        model.addAttribute("pageTitle", "Loading");

        return "front/recommend/loading";
    }
}
