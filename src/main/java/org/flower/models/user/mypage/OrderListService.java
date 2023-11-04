package org.flower.models.user.mypage;

import org.flower.entities.Order;
import org.flower.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrderListService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderListService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // 필요에 따라 다른 메소드 추가
}

