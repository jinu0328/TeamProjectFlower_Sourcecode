package org.flower.controllers.user.mypage.profile;

import lombok.RequiredArgsConstructor;
import org.flower.models.user.UserEditInfo;
import org.flower.models.user.UserEditService;
import org.flower.models.user.UserInfo;
import org.flower.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/user/mypage/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final UserRepository repository;

    @Autowired
    private UserEditService userEditService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    UserRepository userRepository;
    //Model을 만들고, model 객체를 만들어서 UserNo, userNickNm 추가
    @GetMapping("/profilePage")
    public String showMyPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof UserInfo) {
            UserInfo currentUser = (UserInfo) authentication.getPrincipal();
            Long userNo = currentUser.getUserNo();

            //userNo 모델에 추가
            String userNickNm = currentUser.getUserNickNm();
            model.addAttribute("userNickNm", userNickNm);

            // userNo를 사용하여 추가적인 회원 정보를 조회할 수 있습니다.

            // 아래는 단순히 userNo만 모델에 추가하는 예입니다.


            model.addAttribute("userNo", userNo);
        } else {
            // 로그인하지 않은 사용자 또는 기타 상황에 대한 처리
            return "redirect:/user/login"; //
        }

        return "/front/mypage/profile/profilePage"; // mypage.html 또는 mypage.jsp와 같은 뷰 파일을 렌더링
    }

    //닉네임 중복 체크하는 메소드
    @GetMapping("/check/checkNickNm")
    @ResponseBody
    public Map<String, Boolean> checkNickname(@RequestParam String newNickname) {
        boolean exists = repository.isNickNmExists(newNickname);
        Map<String, Boolean> response = new HashMap<>();
        response.put("exists", exists);
        return response;
    }
    //닉네임 업데이트 하는 메소드 
    @PostMapping("/profilePage/update/updateNickname")
    public ResponseEntity<?> updateNickname(@RequestBody UserEditInfo userEditInfo) {
        try {
            UserEditInfo updatedUserEditInfo = userEditService.updateUserNickname(userEditInfo);

            // 사용자 인증 정보 업데이트
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.getPrincipal() instanceof UserInfo) {
                UserInfo currentUser = (UserInfo) authentication.getPrincipal();
                currentUser.setUserNickNm(updatedUserEditInfo.getUserNickNm());
                // 필요한 경우 여기에서 추가 정보를 업데이트

                // 인증 정보를 SecurityContext에 다시 설정
                Authentication newAuth = new UsernamePasswordAuthenticationToken(currentUser, authentication.getCredentials(), authentication.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(newAuth);
            }

            return ResponseEntity.ok(updatedUserEditInfo);

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("An error occurred while updating the nickname: " + e.getMessage());
        }
    }

    @GetMapping("/passwordConfirm")
    public String checkMypassword(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof UserInfo) {
            UserInfo currentUser = (UserInfo) authentication.getPrincipal();
            Long userNo = currentUser.getUserNo();

            //userNo 모델에 추가
            String userNickNm = currentUser.getUserNickNm();
            model.addAttribute("userNickNm", userNickNm);

            // userNo를 사용하여 추가적인 회원 정보를 조회할 수 있습니다.

            // 아래는 단순히 userNo만 모델에 추가하는 예입니다.


            model.addAttribute("userNo", userNo);
        } else {
            // 로그인하지 않은 사용자 또는 기타 상황에 대한 처리
            return "redirect:/user/login"; //
        }

        return "/front/mypage/main/passwordConfirm"; // mypage.html 또는 mypage.jsp와 같은 뷰 파일을 렌더링
    }

    @PostMapping("/password-confirm")
    public String confirmPassword(@RequestParam String password, RedirectAttributes redirectAttributes, Authentication authentication) {
        UserInfo currentUser = (UserInfo) authentication.getPrincipal();
        String storedEncodedPassword = currentUser.getUserPw();

        if (passwordEncoder.matches(password, storedEncodedPassword)) {
            // 비밀번호가 일치하는 경우
            return "redirect:/user/mypage/profile/profilePage";
        } else {
            // 비밀번호가 일치하지 않는 경우
            redirectAttributes.addFlashAttribute("error", "비밀번호가 일치하지 않습니다");
            return "redirect:/user/mypage/profile/passwordConfirm";
        }
    }

}
