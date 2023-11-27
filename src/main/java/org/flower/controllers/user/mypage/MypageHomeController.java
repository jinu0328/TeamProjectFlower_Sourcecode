package org.flower.controllers.user.mypage;


import org.flower.commons.constants.OrderState;
import org.flower.commons.constants.UserRole;
import org.flower.entities.Order;
import org.flower.models.order.order.OrderEditService;
import org.flower.models.order.order.OrderInfoService;
import org.flower.models.user.UserInfo;
import org.flower.repositories.OrderRepository;
import org.flower.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;



@Controller
@RequestMapping("/user/mypage")
public class MypageHomeController {
    @Autowired
    private OrderInfoService orderInfoService;

    @Autowired
    private OrderEditService orderEditService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    /* // 화면 닉네임 동적으로 받아오는 메소드
    @GetMapping
    public String showMyNickname(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof UserInfo) {
            UserInfo currentUser = (UserInfo) authentication.getPrincipal();
            Long userNo = currentUser.getUserNo();



            // 로그인한 사용자의 ACCEPTING 상태인 주문 수 계산
            long acceptingOrderCount = orderRepository.findByUser_UserNo(userNo).stream()
                    .filter(order -> order.getOrderStatus() == OrderState.ACCEPTING)
                    .count();
            // 주문 상태 개수를 모델에 추가
            model.addAttribute("acceptingOrderCount", acceptingOrderCount);

            //ACCEPTED 주문상태 개수 확인
            long acceptedOrderCount= orderRepository.findByUser_UserNo(userNo).stream()
                    .filter(order -> order.getOrderStatus() == OrderState.ACCEPTED)
                    .count();

            // ACCEPTED 상태 개수를 모델에 추가
            model.addAttribute("acceptedOrderCount", acceptedOrderCount);

            //로그인한 사용자의 닉네임을 모델에 추가
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
    } 헤더에서 마이페이지 누르면 바로 주문내역 볼수 있게 일단 주석처리 */

    // 현재 로그인한 사용자의 userNo를 가져오고 모델에 주문 내역 엔티티 추가, 주문 내역 html 페이지 리턴
    @GetMapping("/main/home/orderlist")
    public String showMyOrder(@RequestParam(required = false) String status, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof UserInfo) {
            UserInfo currentUser = (UserInfo) authentication.getPrincipal();
            Long userNo = currentUser.getUserNo();

            String userNickNm = currentUser.getUserNickNm();
            model.addAttribute("userNickNm", userNickNm);
            model.addAttribute("userNo", userNo);

            List<Order> userOrders; // URL의 status 파라미터에 따라 주문 목록을 필터링하거나 모든 주문을 가져옴
            if (status != null && !status.isEmpty()) {
                OrderState orderState = OrderState.valueOf(status.toUpperCase());
                userOrders = orderInfoService.getOrdersByStatus(userNo, orderState);
            } else {
                userOrders = currentUser.getAuthorities().stream() // 사용자의 권한에 따라 OWNER는 모든 주문을, 일반 사용자는 자신의 주문만을 가져옴
                        .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_" + UserRole.OWNER.name()))
                        ? orderInfoService.getAllOrders()
                        : orderInfoService.getOrdersByUserNo(userNo);
            }
            model.addAttribute("userOrders", userOrders);

            long acceptingOrderCountOwner = orderInfoService.countOrdersByStatus(OrderState.ACCEPTING);
            model.addAttribute("acceptingOrderCount", acceptingOrderCountOwner);

            long acceptedOrderCountOwner = orderInfoService.countOrdersByStatus(OrderState.ACCEPTED);
            model.addAttribute("acceptedOrderCount", acceptedOrderCountOwner);

            long preparingOrderCountOwner = orderInfoService.countOrdersByStatus(OrderState.PREPARING);
            model.addAttribute("preparingOrderCount", preparingOrderCountOwner);

            long preparedOrderCountOwner = orderInfoService.countOrdersByStatus(OrderState.PREPARED);
            model.addAttribute("preparedOrderCount", preparedOrderCountOwner);

            long pickedUpOrderCountOwner = orderInfoService.countOrdersByStatus(OrderState.PICKEDUP);
            model.addAttribute("pickedUpOrderCount", pickedUpOrderCountOwner);

            return currentUser.getAuthorities().stream()
                    .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_" + UserRole.OWNER.name()))
                    ? "/front/mypage/main/orderList_Owner"
                    : "/front/mypage/main/home_orderlist";
        } else {
            return "redirect:/user/login";
        }
    }

    // 주문수락 버튼 누르면 orderEditService 호출
    @PostMapping("/orders/accept")
    public ResponseEntity<String> acceptOrder(@RequestParam("orderNo") Long orderNo) { // @RequestParam 사용
        try {
            System.out.println("Attempting to accept order: " + orderNo);
            orderEditService.acceptOrder(orderNo);
            return ResponseEntity.ok("Order accepted");
        } catch (Exception e) {
            System.out.println("Error accepting order: " + e.getMessage());
            e.printStackTrace(); // 로그에 예외를 출력
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error accepting order");
        }
    }

    // 제작 시작 버튼 누르면 orderEditService 호출
    @PostMapping("/orders/start")
    public ResponseEntity<String> startOrder(@RequestParam("orderNo") Long orderNo) { // @RequestParam 사용
        try {
            System.out.println("Attempting to start order: " + orderNo);
            orderEditService.startOrder(orderNo);
            return ResponseEntity.ok("Order started");
        } catch (Exception e) {
            System.out.println("Error starting order: " + e.getMessage());
            e.printStackTrace(); // 로그에 예외를 출력
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error accepting order");
        }
    }

    // 픽업 대기 버튼 누르면 orderEditService 호출
    @PostMapping("/orders/prepared")
    public ResponseEntity<String> preparedOrder(@RequestParam("orderNo") Long orderNo) { // @RequestParam 사용
        try {
            System.out.println("Attempting to prepared order: " + orderNo);
            orderEditService.preparedOrder(orderNo);
            return ResponseEntity.ok("Order prepared");
        } catch (Exception e) {
            System.out.println("Error preparing order: " + e.getMessage());
            e.printStackTrace(); // 로그에 예외를 출력
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error accepting order");
        }
    }

    // 주문 완료 버튼 누르면 orderEditService 호출
    @PostMapping("/orders/pickup")
    public ResponseEntity<String> pickupOrder(@RequestParam("orderNo") Long orderNo) { // @RequestParam 사용
        try {
            System.out.println("Attempting to pickup order: " + orderNo);
            orderEditService.pickupOrder(orderNo);
            return ResponseEntity.ok("wait for pickup");
        } catch (Exception e) {
            System.out.println("Error pickup: " + e.getMessage());
            e.printStackTrace(); // 로그에 예외를 출력
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error accepting order");
        }
    }

}
