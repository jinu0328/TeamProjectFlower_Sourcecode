package org.flower.models.order.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.flower.entities.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderInfo {

    private Long orderNo;
    private Long userNo;
    private String userEmail;
    private String userNm;
    private String userNickNm;
    private String cellPhone;
    private LocalDate pickupDate;
    private LocalTime pickupTime;
    private String flowertype;
    private String flowercolor;
    private String pricerange;
    private String message;
    private String orderStatus;
    private LocalDateTime createdAt;
}
