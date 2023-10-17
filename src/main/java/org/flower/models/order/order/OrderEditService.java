package org.flower.models.order.order;

import jakarta.transaction.Transactional;
import org.flower.commons.constants.OrderState;
import org.flower.entities.Order;
import org.flower.entities.User;
import org.flower.repositories.OrderRepository;
import org.flower.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderEditService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;


    /*
    *  관리자 페이지 주문 추가 기능
    * */
    public void addOrderList(OrderInfo orderInfo){
        // 유저 번호를 먼저 찾습니다.
        User user = userRepository.findByUserNo(orderInfo.getUserNo());
        if(user == null) {
            throw new IllegalArgumentException("No user found with userNo " + orderInfo.getUserNo());
        }

        Order order = Order.builder()
                .user(user)
                .pickupDate(orderInfo.getPickupDate())
                .pickupTime(orderInfo.getPickupTime())
                .flowertype(orderInfo.getFlowertype())
                .flowercolor(orderInfo.getFlowercolor())
                .pricerange(orderInfo.getPricerange())
                .message(orderInfo.getMessage())
                .orderStatus(OrderState.ACCEPTING)
                .build();


        orderRepository.save(order);
    }

    /*
    *   관리자페이지 주문 리스트 수정 기능
    * */
    @Transactional
    public void editOrderList(OrderInfo orderInfo) throws Exception {
        // 주문 정보 검증
        if (orderInfo == null || orderInfo.getOrderNo() == null) {
            throw new IllegalArgumentException("주문 정보가 유효하지 않습니다.");
        }

    }
}
