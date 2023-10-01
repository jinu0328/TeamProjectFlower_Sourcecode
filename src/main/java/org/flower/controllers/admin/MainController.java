package org.flower.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController("/admin")
public class MainController {

    @GetMapping
    public String main(){
        return "admin/main/index";
    }
}
