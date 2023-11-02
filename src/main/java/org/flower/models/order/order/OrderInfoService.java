package org.flower.models.order.order;

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

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }
}
