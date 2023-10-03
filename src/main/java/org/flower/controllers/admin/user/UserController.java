package org.flower.controllers.admin.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/user")
public class UserController {

    /*
    * <회원관리> 클릭시 나오는 페이지
    * == 회원 목록
    *
    *
    * */
    @GetMapping
    public String index(){

        return "admin/user/index";
    }


}
