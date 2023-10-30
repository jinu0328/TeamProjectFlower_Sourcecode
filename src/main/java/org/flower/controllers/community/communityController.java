package org.flower.controllers.community;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/community")
public class communityController {

    @GetMapping
    public String community(){
        return "front/community/main";
    }
}