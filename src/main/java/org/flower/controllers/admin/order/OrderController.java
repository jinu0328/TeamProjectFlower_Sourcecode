package org.flower.controllers.admin.order;

import org.flower.entities.Order;
import org.flower.models.order.order.OrderEditService;
import org.flower.models.order.order.OrderInfo;
import org.flower.models.order.order.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@RequestMapping("/admin/order")
public class OrderController {

    @Autowired
    private OrderInfoService orderInfoService; // 주문과 관련된 비즈니스 로직을 처리하는 서비스 클래스

    @Autowired
    private OrderEditService orderEditService;

    @GetMapping("/orderList")
    public String orderlist(Model model) {
        List<Order> orderList = orderInfoService.getAllOrders(); // 주문 목록을 가져오는 서비스 메서드 호출

        model.addAttribute("orders", orderList); // 모델에 주문 목록을 추가하여 뷰로 전
        return "admin/order/index";
    }

    @PostMapping("/orderList")
    public ResponseEntity<String> addOrderList(@RequestBody OrderInfo orderInfo){
        System.out.println(orderInfo);
        try {
            orderEditService.addOrderList(orderInfo);
            // 정상 응답인 경우에도 JSON 형식의 응답 본문을 전달합니다.
            return new ResponseEntity<>("{\"message\":\"주문 리스트가 성공적으로 추가되었습니다.\"}", HttpStatus.OK);
        }catch (Exception e){
            // 에러 응답의 경우에도 마찬가지입니다.
            return new ResponseEntity<>("{\"message\":\"주문 리스트 추가 중 에러가 생겼습니다.\"}",  HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
