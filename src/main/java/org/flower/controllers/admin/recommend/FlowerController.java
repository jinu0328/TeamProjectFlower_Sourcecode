package org.flower.controllers.admin.recommend;

import org.flower.entities.Flower;
import org.flower.models.recommend.flower.FlowerEditService;
import org.flower.models.recommend.flower.FlowerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/recommend")
public class FlowerController {

    @Autowired
    private FlowerInfoService flowerInfoService;

    @Autowired
    private FlowerEditService flowerEditService;

    // 꽃 관련 메소드들
    // 들어가면 바로 꽃 리스트 보이게 하려고 주소 추가로 할당 안함
    @GetMapping()
    public String flowerList(Model model){
        List<Flower> flowersList = flowerInfoService.getAllFlowers();
        model.addAttribute("flowersList", flowersList);
        return "admin/recommend/index";
    }
    // 꽃 추가 - Post
    @PostMapping("/addFlower")
    public String addFlower(@RequestParam String flowerNm, String flowerMean, String bloomseason, String season, String flowerIamges, RedirectAttributes redirectAttributes) {
        try {
            flowerEditService.addFlower(flowerNm, flowerMean, bloomseason, season, flowerIamges);
            redirectAttributes.addFlashAttribute("successMessage", "꽃 성공적으로 추가되었습니다.");
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("errorMessage", "꽃 추가 중 오류가 발생했습니다.");
        }
        return "redirect:/admin/recommend";
    }

}
