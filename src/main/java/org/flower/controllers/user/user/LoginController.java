package org.flower.controllers.user.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/user/login")
public class LoginController {

    @GetMapping
    public String login(String success, Model model){
        UserLogin userLogin = new UserLogin();
        userLogin.setSuccess(success);

        model.addAttribute("userLogin", userLogin);
        model.addAttribute("addCss", new String[]{"user/style"});
        model.addAttribute("pageTitle", "로그인");

        return "front/user/login";
    }
}
