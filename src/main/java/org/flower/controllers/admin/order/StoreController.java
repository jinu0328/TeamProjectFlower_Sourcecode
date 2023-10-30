package org.flower.controllers.admin.order;

import org.flower.entities.Store;
import org.flower.models.order.order.OrderEditInfo;
import org.flower.models.order.order.OrderInfo;
import org.flower.models.order.store.StoreEditInfo;
import org.flower.models.order.store.StoreEditService;
import org.flower.models.order.store.StoreInfo;
import org.flower.models.order.store.StoreInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/order")
public class StoreController {

    @Autowired
    private StoreInfoService storeInfoService;

    @Autowired
    private StoreEditService storeEditService;

    @GetMapping("/storeList")
    public String storeList(Model model){
        List<Store> storeList = storeInfoService.getAllStores(); // 매장 목록을 가져오는 서비스 메서드 호출

        model.addAttribute("stores", storeList);
        return "admin/order/store";
    }

    /*
    매장 추가 - GET
     */
    @GetMapping("/addStoreList")
    public String showAddStoreForm() {
        return "admin/order/addstorelist"; // 매장 추가 페이지 템플릿 경로
    }

    /*
    매장 추가 - POST
     */
    @PostMapping("/addStoreList")
    public String addStoreList(@ModelAttribute StoreInfo storeInfo, RedirectAttributes redirectAttributes) {
        try {
            storeEditService.addStoreList(storeInfo);
            redirectAttributes.addFlashAttribute("status", "success");
            redirectAttributes.addFlashAttribute("message", "매장이 성공적으로 추가되었습니다.");
            return "redirect:/admin/order/storeList";
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("status", "error");
            redirectAttributes.addFlashAttribute("message", "매장 추가 중 오류가 발생했습니다: " + e.getMessage());
            return "redirect:/admin/order/addStoreList";
        }
    }

    /*
     *   매장 수정 - POST
     * */
    @PostMapping("/editStoreList")
    public ResponseEntity<?> editStores(@RequestBody List<StoreInfo> storeInfo) {
        System.out.println(storeInfo);
        try {
            List<StoreEditInfo> updatedStoreInfos = storeEditService.editStoreList(storeInfo);
            return ResponseEntity.ok(updatedStoreInfos);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /*
     * 매장 삭제 - DELETE
     *
     * */
    @DeleteMapping("/deleteStores")
    public ResponseEntity<Map<String, String>> deleteSelectedStores(@RequestBody List<Long> storeIds) {
        Map<String, String> response = new HashMap<>();
        try {
            storeEditService.deleteStores(storeIds);
            response.put("message", "선택된 매장이 성공적으로 삭제되었습니다.");
            return ResponseEntity.ok(response);
        } catch(Exception e) {
            e.printStackTrace();
            response.put("message", "매장 삭제 중 오류가 발생했습니다.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


}
