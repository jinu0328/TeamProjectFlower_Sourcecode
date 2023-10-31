package org.flower.controllers.admin.community;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/community")
public class communityListController {

    @GetMapping
    public String community(){
        return "admin/community/index";
    }
}
