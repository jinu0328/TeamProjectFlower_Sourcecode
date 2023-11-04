package org.flower.controllers.user.mypage;


import org.flower.entities.Order;
import org.flower.models.order.order.OrderInfoService;
import org.flower.models.user.UserInfo;
import org.flower.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user/mypage")
public class MypageHomeController {
    @Autowired
    private OrderInfoService orderInfoService;

    @Autowired
    UserRepository userRepository;
    @GetMapping
    public String showMyPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof UserInfo) {
            UserInfo currentUser = (UserInfo) authentication.getPrincipal();
            Long userNo = currentUser.getUserNo();

            // 로그인한 사용자의 주문 목록을 가져와서 모델에 추가
            List<Order> userOrders = orderInfoService.getOrdersByUserNo(userNo);
            model.addAttribute("userOrders", userOrders);

            // 로그인한 사용자의 닉네임을 모델에 추가
            String userNickNm = currentUser.getUserNickNm();
            model.addAttribute("userNickNm", userNickNm);

            // userNo를 사용하여 추가적인 회원 정보를 조회할 수 있습니다.
            // 예: userProfile, userPosts 등
            // 아래는 단순히 userNo만 모델에 추가하는 예입니다.
            model.addAttribute("userNo", userNo);
        } else {
            // 로그인하지 않은 사용자 또는 기타 상황에 대한 처리
            return "redirect:/user/login"; //
        }

        return "/front/mypage/main/home"; // mypage.html 또는 mypage.jsp와 같은 뷰 파일을 렌더링
    }

}
