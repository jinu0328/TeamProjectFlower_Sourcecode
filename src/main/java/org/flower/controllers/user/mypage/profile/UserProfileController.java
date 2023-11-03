package org.flower.controllers.user.mypage.profile;

import lombok.RequiredArgsConstructor;
import org.flower.entities.User;
import org.flower.models.user.UserEditInfo;
import org.flower.models.user.UserEditService;
import org.flower.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user/mypage/profile")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserRepository repository;

    @Autowired
    private UserEditService userEditService;

    @GetMapping("/check/checkNickNm")
    @ResponseBody
    public Map<String, Boolean> checkNickname(@RequestParam String newNickname) {
        boolean exists = repository.isNickNmExists(newNickname);
        Map<String, Boolean> response = new HashMap<>();
        response.put("exists", exists);
        return response;
    }

    // 닉네임 변경 요청을 처리하는 메소드
    @PostMapping("/profilePage/update/updateNickname")
    public ResponseEntity<?> updateNickname(@RequestBody UserEditInfo userEditInfo) {System.out.println("dddddddddddsssssssssssssfffffffffffff");
        try {

            UserEditInfo updatedUserEditInfo = userEditService.editUserNickname(userEditInfo);
            return ResponseEntity.ok().body(updatedUserEditInfo);
        } catch (Exception e) {
            // 예외 발생 시 클라이언트에게 오류 메시지와 함께 500 Internal Server Error 상태 코드를 전송
            return ResponseEntity.internalServerError().body("An error occurred while updating the nickname: " + e.getMessage());
        }
    }
}