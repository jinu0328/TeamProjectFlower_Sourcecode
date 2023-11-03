
package org.flower.controllers.order;

import org.flower.entities.Order;
import org.flower.models.order.order.OrderInfoService;
import org.flower.models.order.orderform.OrderFormInfo;
import org.flower.models.order.orderform.OrderFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/order")
public class OrderprocessController {

    @Autowired
    private OrderFormService orderFormService;

    @Autowired
    private OrderInfoService orderInfoService;


    /*
    * 주문서 작성 페이지
    * */
    @GetMapping()
    public String orderForm(Model model) {

        model.addAttribute("pageTitle", "주문서 작성");
        model.addAttribute("orderForm", new OrderFormInfo()); // 주문서 폼 객체 추가

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

