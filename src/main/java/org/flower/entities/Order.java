package org.flower.entities;


import jakarta.persistence.*;
import lombok.*;
import org.flower.commons.constants.OrderState;
import org.flower.models.order.order.OrderInfo;

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
    private User user;

    /*
    @Column(nullable = true, length = 45)
    private String userEmail; // 아이디(이메일)

    @Column(nullable = true, length = 35)
    private String userNm; // 회원명

    @Column(nullable = true, length = 40)
    private String userNickNm; // 닉네임

    @Column(nullable = true, length = 20)
    private String cellPhone;       // 전화번호

    */

    public String getUserEmail() {
        return user.getUserEmail();
    }

    public String getUserNm() {
        return user.getUserNm();
    }

    public String getUserNickNm() {
        return user.getUserNickNm();
    }

    public String getCellPhone() {
        return user.getCellPhone();
    }

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

    @Enumerated(EnumType.STRING)
    @Column(nullable = true, length = 30)
    private OrderState orderStatus = OrderState.ACCEPTING;  // 'ACCEPTING', 'ACCEPTED', 'PREPARING', 'PREPARED' , 'PICKEDUP' 중 하나의 상태
                                                            // 기본값은 '접수중'

}
