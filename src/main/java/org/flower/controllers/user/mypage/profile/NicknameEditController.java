//package org.flower.controllers.user.mypage.profile;
//
//import lombok.RequiredArgsConstructor;
//import org.flower.controllers.user.user.UserEditValidator;
//import org.flower.controllers.user.user.UserJoinValidator;
//import org.flower.repositories.UserRepository;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Controller
//@RequestMapping("/user/mypage/profile/profilePage")
//@RequiredArgsConstructor
//public class NicknameEditController {
//
//    private final UserEditValidator userEditValidator;
//
//    private final UserRepository repository;
//
//    @GetMapping("/checkNickNm")
//    @ResponseBody
//    public Map<String, Boolean> checkNickname(@RequestParam String newNickname) {
//        boolean exists = repository.isNickNmExists(newNickname);
//        Map<String, Boolean> response = new HashMap<>();
//        response.put("exists", exists);
//        return response;
//    }
//}
