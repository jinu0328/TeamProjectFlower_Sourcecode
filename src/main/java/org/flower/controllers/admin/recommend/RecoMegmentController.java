package org.flower.controllers.admin.recommend;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/recommend")
public class RecoMegmentController {
    @Autowired
    private KeywordInfoService keywordInfoService;

    // 들어가면 바로 꽃 리스트 보이게 하려고 주소 추가로 할당 안함
    @GetMapping
    public String flowerList(){

        return "admin/recommend/index";
    }

    @GetMapping("/keyword")
    public String keyword(Model model){
        List<Keywords> keywordsList = keywordInfoService.getAllKeywords();
        model.addAttribute("keywordsList", keywordsList);
        return "admin/recommend/keyword";
    }

    /**
    * 키워드 추가 - POST
    *
    * */
    @PostMapping("/keyword")
    public String addKeyword(@RequestParam String keywordNm, RedirectAttributes redirectAttributes){
        try {
            keywordInfoService.addKeyword(keywordNm);
            redirectAttributes.addFlashAttribute("successMessage", "키워드가 성공적으로 추가되었습니다.");
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("errorMessage", "키워드 추가 중 오류가 발생했습니다.");
        }
        return "redirect:/admin/recommend/keyword";
    }

    @GetMapping("/flowerWeight")
    public String flowerWeight(){

        return "admin/recommend/flowerWeight";
    }
}
