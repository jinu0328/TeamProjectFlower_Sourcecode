package org.flower.models.order.order;

import org.flower.commons.constants.OrderState;
import org.flower.commons.constants.UserRole;
import org.flower.entities.Order;
import org.flower.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    // 주문 상태별 개수를 계산하는 메서드
    public long countUserOrdersByStatus(Long userNo, UserRole userRole, OrderState orderState) {
        if (userRole == UserRole.OWNER) {
            // OWNER는 모든 주문을 카운트
            return orderRepository.findAll().stream()
                    .filter(order -> order.getOrderStatus() == orderState)
                    .count();
        } else {
            // USER는 자신의 주문만 카운트
            return orderRepository.findByUser_UserNo(userNo).stream()
                    .filter(order -> order.getOrderStatus() == orderState)
                    .count();
        }
    }

    // 선택된 주문 상태에 따라 주문 목록을 반환하는 메서드
    public List<Order> getOrdersByStatusByuserNo(Long userNo, OrderState orderState) {
        return orderRepository.findAll().stream()
                .filter(order -> order.getOrderStatus() == orderState && order.getUser().getUserNo().equals(userNo))
                .collect(Collectors.toList());
    }

    public List<Order> getOrdersByStatus(OrderState orderState) {
        return orderRepository.findAll().stream()
                .filter(order -> order.getOrderStatus() == orderState)
                .collect(Collectors.toList());
    }
}
