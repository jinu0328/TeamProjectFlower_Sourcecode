package org.flower.models.order.order;

import jakarta.transaction.Transactional;
import org.flower.commons.constants.OrderState;
import org.flower.entities.Order;
import org.flower.entities.User;
import org.flower.repositories.OrderRepository;
import org.flower.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public List<OrderEditInfo> editOrderList(List<OrderInfo> orderInfos) throws Exception {
        List<OrderEditInfo> updatedOrderInfos = new ArrayList<>();

        for (OrderInfo orderInfo : orderInfos) {
            Order order = orderRepository.findById(orderInfo.getOrderNo())
                    .orElseThrow(() -> new Exception("Order with ID " + orderInfo.getOrderNo() + " not found"));

            // Update order fields based on orderInfo
            order.setPickupDate(orderInfo.getPickupDate());
            order.setPickupTime(orderInfo.getPickupTime());
            order.setFlowertype(orderInfo.getFlowertype());
            order.setFlowercolor(orderInfo.getFlowercolor());
            order.setPricerange(orderInfo.getPricerange());
            order.setMessage(orderInfo.getMessage());
            order.setOrderStatus(OrderState.valueOf(orderInfo.getOrderStatus()));
            order = orderRepository.save(order);

            // Convert updated order to SimpleOrderInfo and add to the list
            OrderEditInfo updatedOrderInfo = new OrderEditInfo(
                    order.getOrderNo(),
                    order.getPickupDate(),
                    order.getPickupTime(),
                    order.getFlowertype(),
                    order.getFlowercolor(),
                    order.getPricerange(),
                    order.getMessage(),
                    order.getOrderStatus().name()
            );
            updatedOrderInfos.add(updatedOrderInfo);
        }

        return updatedOrderInfos;
    }

    /*
    *   관리자 페이지 주문 리스트 삭제 기능
    * */
    @Transactional
    public void deleteOrders(List<Long> orderIds) {
        if(orderIds == null || orderIds.isEmpty()) {
            throw new IllegalArgumentException("Order IDs cannot be null or empty");
        }

        // 주문을 찾아서 삭제합니다.
        for(Long orderId : orderIds) {
            if(!orderRepository.existsById(orderId)) {
                throw new IllegalArgumentException("Order with ID " + orderId + " not found");
            }
            orderRepository.deleteById(orderId);
        }
    }
}
