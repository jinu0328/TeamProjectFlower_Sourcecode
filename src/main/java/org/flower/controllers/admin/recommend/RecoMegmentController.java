package org.flower.controllers.admin.recommend;

import org.flower.entities.Keywords;
import org.flower.repositories.KeywordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/recommend")
public class RecoMegmentController {
    @Autowired
    private KeywordRepository keywordRepository;

    // 들어가면 바로 꽃 리스트 보이게 하려고 주소 추가로 할당 안함
    @GetMapping
    public String flowerList(){

        return "admin/recommend/index";
    }

    @GetMapping("/keyword")
    public String keyword(Model model){
        List<Keywords> keywordsList = keywordRepository.findAll();
        model.addAttribute("keywordsList", keywordsList);
        return "admin/recommend/keyword";
    }

    @GetMapping("/flowerWeight")
    public String flowerWeight(){

        return "admin/recommend/flowerWeight";
    }
}
