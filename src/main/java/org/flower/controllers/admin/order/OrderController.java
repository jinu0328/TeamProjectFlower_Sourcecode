package org.flower.controllers.admin.order;

import org.flower.entities.Order;
import org.flower.models.order.FlowerOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@RequestMapping("/admin/order")
public class OrderController {

    @Autowired
    private FlowerOrder flowerOrder; // 주문과 관련된 비즈니스 로직을 처리하는 서비스 클래스

    @GetMapping
    public String orderlist(Model model) {
        List<Order> orderList = flowerOrder.getAllOrders(); // 주문 목록을 가져오는 서비스 메서드 호출

        model.addAttribute("orders", orderList); // 모델에 주문 목록을 추가하여 뷰로 전
        return "admin/order/index";
    }
}
