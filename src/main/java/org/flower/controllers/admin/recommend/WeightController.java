package org.flower.controllers.admin.recommend;

import org.flower.entities.Flower;
import org.flower.entities.FlowerWeight;
import org.flower.models.recommend.flower.FlowerInfo;
import org.flower.models.recommend.flower.FlowerInfoService;
import org.flower.models.recommend.keyword.KeywordInfo;
import org.flower.models.recommend.weight.WeightEditService;
import org.flower.models.recommend.weight.WeightInfo;
import org.flower.models.recommend.weight.WeightInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/recommend")
public class WeightController {

    @Autowired
    private WeightInfoService weightInfoService;

    @Autowired
    private FlowerInfoService flowerInfoService;

    @Autowired
    private WeightEditService weightEditService;

    // 꽃 리스트
    @GetMapping("/weight")
    public String flowerList(Model model){
        List<FlowerWeight> weightsList = weightInfoService.getAllWeights();
        // List<Flower> flowersList = flowerInfoService.getAllFlowers();
        // model.addAttribute("flowersList", flowersList);
        Comparator<FlowerWeight> comp = new Comparator<FlowerWeight>() {
            @Override
            public int compare(FlowerWeight o1, FlowerWeight o2) {
                if(o1.getFlower().getFlowerNo() == o2.getFlower().getFlowerNo()) {
                    return (int)(o1.getKeyword().getKeywordNo() - o2.getKeyword().getKeywordNo());
                }
                return (int)(o1.getFlower().getFlowerNo() - o2.getFlower().getFlowerNo());
            }
        };
        weightsList.sort(comp);

        model.addAttribute("weightsList", weightsList);
        return "admin/recommend/flowerWeightList";
    }

    @PostMapping("/editWeight")
    public ResponseEntity<Map<String, Object>> editWeight(@RequestBody WeightInfo weightInfo){
        Map<String, Object> response = new HashMap<>();
        try {
            weightEditService.editWeight(weightInfo);
            response.put("success", true);
            response.put("message", "가중치가 성공적으로 수정되었습니다.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "가중치 수정 중 오류가 발생했습니다.");
            e.printStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
    @GetMapping("/{flowerNo}")
    public ResponseEntity<List<FlowerWeight>> getWeightsByFlowerNo(@PathVariable Long flowerNo) {
        List<FlowerWeight> weights = weightInfoService.getByFlowerNo(flowerNo);
        return ResponseEntity.ok(weights);
    }
    */


//
//    @GetMapping("/flowerList/weight")
//    public String flowerWeight(Model model){
//
//        return "admin/recommend/weight";
//    }

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
