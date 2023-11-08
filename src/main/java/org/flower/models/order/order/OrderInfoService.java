package org.flower.models.order.order;

import org.flower.commons.constants.UserRole;
import org.flower.entities.Order;
import org.flower.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderInfoService {

    @Autowired
    private OrderRepository orderRepository;
    
    // 주문 관련 모든 내용을 가져옴
    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }
    public List<Order> getOrdersByUserNo(Long userNo) {
        return orderRepository.findByUser_UserNo(userNo);
    }

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    public List<Order> getOrdersByUserRole(Long userNo, UserRole userRole) {
        if (userRole == UserRole.OWNER) {
            // OWNER인 경우 모든 주문을 반환
            return orderRepository.findAll();
        } else {
            // USER인 경우 해당 사용자의 주문만 반환
            return orderRepository.findByUser_UserNo(userNo);
        }
    }
}
