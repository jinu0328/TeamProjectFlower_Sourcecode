package org.flower.controllers.user.mypage.Review;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/mypage/main/main_review")
public class ReviewController {
    @GetMapping
    public String main(){
        return "/front/mypage/main/main_review";
    }
}
