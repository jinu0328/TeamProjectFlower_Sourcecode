package org.flower.controllers.admin.order;

import org.flower.entities.Store;
import org.flower.models.order.store.StoreInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/order")
public class StoreController {

    @Autowired
    private StoreInfoService storeInfoService;

    @GetMapping("/store")
    public String storeList(Model model){
        List<Store> storeList = storeInfoService.getAllStores(); // 매장 목록을 가져오는 서비스 메서드 호출

        model.addAttribute("stores", storeList);
        return "admin/order/store";
    }

}
