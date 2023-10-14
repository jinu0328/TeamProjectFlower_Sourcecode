package org.flower.controllers.admin.order;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class OrderController {

    @GetMapping("/admin/order")
    public String orderManagementPage() {
        return "admin/order/index";
    }
}
