package org.flower.controllers.admin.recommend;

import org.flower.entities.Flower;
import org.flower.models.recommend.flower.FlowerEditService;
import org.flower.models.recommend.flower.FlowerInfoService;
import org.flower.models.recommend.flower.FlowerInfo;
import org.flower.models.recommend.keyword.KeywordInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/recommend")
public class FlowerController {

    @Autowired
    private FlowerInfoService flowerInfoService;

    @Autowired
    private FlowerEditService flowerEditService;

    // 들어가면 바로 꽃 리스트 보이게 하려고 주소 추가로 할당 안함
    @GetMapping()
    public String flowerList(Model model){
        List<Flower> flowersList = flowerInfoService.getAllFlowers();
        model.addAttribute("flowersList", flowersList);
        return "admin/recommend/index";
    }
    // 꽃 추가 - Post
    @PostMapping("/addFlower")
    public String addFlower(@ModelAttribute FlowerInfo flowerInfo, RedirectAttributes redirectAttributes) {
        try {
            flowerEditService.addFlower(flowerInfo);
            redirectAttributes.addFlashAttribute("successMessage", "꽃 성공적으로 추가되었습니다.");
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("errorMessage", "꽃 추가 중 오류가 발생했습니다.");
        }

        return "redirect:/admin/recommend";
    }

    @PostMapping("/editFlower")
    public ResponseEntity<Map<String, Object>> editFlower(@RequestBody FlowerInfo flowerInfo){
        Map<String, Object> response = new HashMap<>();
        try {
            flowerEditService.editFlower(flowerInfo);
            response.put("success", true);
            response.put("message", "꽃이 성공적으로 수정되었습니다.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "꽃 수정 중 오류가 발생했습니다.");
            e.printStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteFlower")
    public ResponseEntity<Map<String, Object>> deleteFlower(@RequestBody Map<String, List<Long>> payload){
        Map<String, Object> response = new HashMap<>();
        try {
            List<Long> flowerNos = payload.get("flowerNos");
            flowerEditService.deleteFlower(flowerNos);
            response.put("success", true);
            response.put("message", "꽃 성공적으로 삭제되었습니다.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            response.put("success", false);
            response.put("message", "꽃 삭제 중 오류가 발생했습니다.");
            e.printStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
