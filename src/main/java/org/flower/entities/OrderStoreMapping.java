package org.flower.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="order_store_mapping")
public class OrderStoreMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long matchingNo;  // 매칭 번호, primary key

    @OneToOne
    @JoinColumn(name = "orderNo", nullable = false)
    private Order order;  // 주문 엔터티와의 관계 설정

    @OneToOne
    @JoinColumn(name = "storeNo", nullable = false)
    private Store store;  // 매장 엔터티와의 관계 설정

    @Column(nullable = false)
    private LocalDateTime matchedAt;  // 매칭된 시간

    // 다른 필요한 필드들은 여기에 추가...
}
