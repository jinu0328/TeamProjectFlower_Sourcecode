package org.flower.controllers.mypage.profile;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mypage/profile/profilePage")
public class ProfileController {
    @GetMapping
    public String main(){
        return "/mypage/profile/profilePage";
    }
}
