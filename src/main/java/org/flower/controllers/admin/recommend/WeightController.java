package org.flower.controllers.admin.recommend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/recommend")
public class WeightController {

    // 꽃 가중치 리스트 관리
    @GetMapping("/weight")
    public String flowerWeight(){

        return "admin/recommend/flowerWeight";
    }

}
