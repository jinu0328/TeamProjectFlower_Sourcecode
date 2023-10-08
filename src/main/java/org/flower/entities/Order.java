package org.flower.entities;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="orders")    // 'orders' 속성으로 해당 엔티티가 매핑될 테이블의 이름을 지정함
public class Order extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderNo;            // 주문 식별자

    @ManyToOne
    @JoinColumn(name = "userNo" , nullable = false)
    private User user; // 주문한 사용자의 ID

    @Column(nullable = false, length = 20)
    private LocalDate pickupDate;       // 주문 픽업 날짜

    @Column(nullable = false, length = 20)
    private LocalTime pickupTime;       // 주문 픽업 시간

    @Column(nullable = false, length = 30)
    private String flowertype;       // 선호하는 꽃 종류

    @Column(nullable = false, length = 30)
    private String flowercolor;       // 선호하는 꽃 색상

    @Column(nullable = false, length = 20)
    private String pricerange;       // 주문의 가격 범위

    @Column(length = 255)
    private String message;       // 고객이 전달하고 싶은 메세지 내용

    // 주문의 현재 상태를 '접수중', '매장 선택중','매칭 실패', '매칭 완료' 로 제한


}
