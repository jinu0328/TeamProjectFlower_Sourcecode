
package org.flower.models.order.orderform;

import jakarta.transaction.Transactional;
import org.flower.commons.constants.OrderState;
import org.flower.entities.Order;
import org.flower.entities.User;
import org.flower.models.user.UserInfo;
import org.flower.repositories.OrderRepository;
import org.flower.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class OrderFormService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public Order submitOrderForm(OrderFormInfo orderFormInfo) {
        // 주문 폼 정보를 바탕으로 새로운 주문 객체를 생성
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserInfo currentUser = (UserInfo) authentication.getPrincipal();
        User user = userRepository.findByUserNo(currentUser.getUserNo());
        if(user == null) {
            throw new IllegalArgumentException("No user found with userNo " + currentUser.getUserNo());
        }

        Order newOrder = Order.builder()
                .user(user)
                .pickupDate(orderFormInfo.getPickupDate())
                .pickupTime(orderFormInfo.getPickupTime())
                .flowertype(orderFormInfo.getFlowertype())
                .flowercolor(orderFormInfo.getFlowercolor())
                .pricerange(orderFormInfo.getPricerange())
                .message(orderFormInfo.getMessage())
                .orderStatus(OrderState.ACCEPTING) // 기본 주문 상태는 '접수중'
                .build();

        // 주문 정보 저장
        return orderRepository.save(newOrder);
    }
}
