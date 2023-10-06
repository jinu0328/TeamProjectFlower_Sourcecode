package org.flower.controllers.user.mypage.orderlist;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/mypage/main/home")
public class OrderListController {

    @GetMapping
    public String main(){
        return "/front/mypage/main/home";
    }
}
