package org.flower.controllers.admin.user;

import lombok.Data;
import org.flower.entities.User;
import org.flower.models.user.UserInfo;
import org.flower.models.user.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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
    public String index(Model model){

        return "admin/user/index";
    }


}
