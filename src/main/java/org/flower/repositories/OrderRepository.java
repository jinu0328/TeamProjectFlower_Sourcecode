package org.flower.repositories;


import org.flower.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long>, QuerydslPredicateExecutor<Order> {
    List<Order> findByUser_UserNo(Long userNo); // 사용자 번호로 주문 목록 조회
}
