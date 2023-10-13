package org.flower.controllers.recommend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/flower")
public class FlowerResultController {

    /*
    * 꽃 추천 리스트 보여주는 역할
    * */
    @GetMapping("/result")
    public String flowerResult(){

        return "front/recommend/flower";
    }
}
