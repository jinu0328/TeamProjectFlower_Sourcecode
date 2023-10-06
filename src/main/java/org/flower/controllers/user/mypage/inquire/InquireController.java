package org.flower.controllers.user.mypage.inquire;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/mypage/main/main_inquire")
public class InquireController {
    @GetMapping
    public String main(){
        return "/front/mypage/main/main_inquire";
    }
}
