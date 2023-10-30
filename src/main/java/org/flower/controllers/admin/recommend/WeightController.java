package org.flower.controllers.admin.recommend;

import org.flower.entities.Flower;
import org.flower.entities.FlowerWeight;
import org.flower.entities.Keywords;
import org.flower.models.recommend.weight.WeightInfoService;
import org.flower.repositories.WeightRepository;
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
public class WeightController {

    @Autowired
    private WeightInfoService weightInfoService;

    // 꽃 가중치 리스트 관리
    @GetMapping("/weight")
    public String flowerWeight(Model model){
        List<FlowerWeight> weightsList = weightInfoService.getAllWeights();
        model.addAttribute("weightsList", weightsList);
        return "admin/recommend/flowerWeight";
    }

    /*@PostMapping("/weight")
    public String addFlowerWeight(@RequestParam("flower") Flower flower,
                                  @RequestParam("keyword") Keywords keyword,
                                  @RequestParam("weight") Integer weight,
                                  RedirectAttributes redirectAttributes) {
        FlowerWeight savedFlowerWeight = weightInfoService.addFlowerWeight(flower, keyword, weight);

        // 성공 메시지나 다른 정보를 전달할 수 있습니다.
        redirectAttributes.addFlashAttribute("message", "Weight added successfully!");

        return "redirect:/admin/recommend/weight";  // 리스트 페이지로 리다이렉트
    }*/

}
