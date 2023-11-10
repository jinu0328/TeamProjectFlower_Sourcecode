
package org.flower.controllers.order;

import org.flower.entities.Flower;
import org.flower.entities.Order;
import org.flower.models.order.order.OrderInfoService;
import org.flower.models.order.orderform.OrderFormInfo;
import org.flower.models.order.orderform.OrderFormService;
import org.flower.models.recommend.flower.FlowerInfoService;
import org.flower.models.user.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/order")
public class OrderprocessController {

    @Autowired
    private OrderFormService orderFormService;

    @Autowired
    private OrderInfoService orderInfoService;

    @Autowired
    private FlowerInfoService flowerInfoService;


    /*
    * 주문서 작성 페이지
    * */
    @GetMapping()
    public String orderForm(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication.getPrincipal() instanceof UserInfo)) {
            return "redirect:/user/login"; // 로그인하지 않은 경우 로그인 페이지로 리다이렉트
        }

        UserInfo currentUser = (UserInfo) authentication.getPrincipal();
        List<Flower> flowers = flowerInfoService.getAllFlowers(); // Assuming this returns a list of all flowers.
        model.addAttribute("flowers", flowers);


        OrderFormInfo orderFormInfo = OrderFormInfo.builder()
                .userNo(currentUser.getUserNo()) // 회원 번호 설정
                .userNm(currentUser.getUserNm()) // 사용자 이름 설정
                .cellPhone(currentUser.getCellPhone()) // 사용자 전화번호 설정
                .build();
        model.addAttribute("pageTitle", "주문서 작성");
        model.addAttribute("orderForm", orderFormInfo); // 주문서 폼 객체 추가

        return "front/order/orderForm";
    }


    /*
    * 주문서 저장 및 주문완료 페이지로 리다이액션
    * */
    @PostMapping("/submitOrder")
    public String submitOrder(@ModelAttribute OrderFormInfo orderFormInfo, Model model) {

        model.addAttribute("pageTitle", "주문 완료");
        model.addAttribute("message", "주문이 완료되었습니다.");

        Order completedOrder = orderFormService.submitOrderForm(orderFormInfo);
        model.addAttribute("completedOrder", completedOrder);

        return "front/order/orderCompleted"; // 주문 완료 페이지로 이동
    }
}

