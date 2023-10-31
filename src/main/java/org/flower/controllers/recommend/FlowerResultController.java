package org.flower.controllers.recommend;

import org.flower.entities.Flower;
import org.flower.models.recommend.flower.FlowerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/flower")
public class FlowerResultController {

    @Autowired
    private FlowerInfoService flowerInfoService;

    /*
    * 꽃 추천 리스트 보여주는 역할
    * */
    @GetMapping("/result")
    public String flowerResult(Model model){
        List<Flower> flowerList = flowerInfoService.getAllFlowers();

        // 꽃을 모델에 추가하여 뷰에서 사용
        model.addAttribute("pageTitle","꽃 추천");
        model.addAttribute("flowers", flowerList);

        return "front/recommend/flower";
    }
}
