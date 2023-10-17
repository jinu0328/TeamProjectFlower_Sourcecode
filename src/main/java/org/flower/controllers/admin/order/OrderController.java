package org.flower.controllers.admin.order;

import org.flower.entities.Order;
import org.flower.models.order.order.OrderEditService;
import org.flower.models.order.order.OrderInfo;
import org.flower.models.order.order.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/order")
public class OrderController {

    @Autowired
    private OrderInfoService orderInfoService; // 주문과 관련된 비즈니스 로직을 처리하는 서비스 클래스

    @Autowired
    private OrderEditService orderEditService;

    /*
    *   주문 리스트 - GET
    * */
    @GetMapping("/orderList")
    public String orderlist(Model model) {
        List<Order> orderList = orderInfoService.getAllOrders(); // 주문 목록을 가져오는 서비스 메서드 호출

        model.addAttribute("orders", orderList); // 모델에 주문 목록을 추가하여 뷰로 전
        return "admin/order/index";
    }

    /*
    *   주문추가 - GET
    * */
    @GetMapping("/addOrderList")
    public String showAddOrderForm() {
        return "admin/order/addorderlist"; // 주문 추가 페이지 템플릿 경로
    }

    /*
    *   주문추가 - POST
    * */
    @PostMapping("/addOrderList")
    public String addOrderList(@ModelAttribute OrderInfo orderInfo, RedirectAttributes redirectAttributes) {
        try {
            orderEditService.addOrderList(orderInfo);
            redirectAttributes.addFlashAttribute("status", "success");
            redirectAttributes.addFlashAttribute("message", "주문이 성공적으로 추가되었습니다.");
            return "redirect:/admin/order/orderList";
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("status", "error");
            redirectAttributes.addFlashAttribute("message", "주문 추가 중 오류가 발생했습니다: " + e.getMessage());
            return "redirect:/admin/order/addOrderList";
        }
    }

    /*
     *   주문 수정 - POST
     * */
    @PostMapping("/editOrderList")
    public ResponseEntity<Map<String, Object>> editOrder(@RequestBody OrderInfo orderInfo) {
        Map<String, Object> response = new HashMap<>();
        try {
            orderEditService.editOrderList(orderInfo);
            response.put("success", true);
            response.put("message", "주문 리스트가 성공적으로 수정되었습니다.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "키워드 수정 중 오류가 발생했습니다.");
            e.printStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
