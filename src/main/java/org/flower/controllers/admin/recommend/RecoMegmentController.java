package org.flower.controllers.admin.recommend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/recommend")
public class RecoMegmentController {

    @GetMapping("/list")
    public String flowerList(){

        return "admin/recommend/index";
    }

    @GetMapping("/keyword")
    public String keyword(){

        return "admin/recommend/keyword";
    }

    @GetMapping("/flowerWeight")
    public String flowerWeight(){

        return "admin/recommend/flowerWeight";
    }
}
