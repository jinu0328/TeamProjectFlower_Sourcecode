package org.flower.models.order.orderform;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderFormInfo {

    /*
    * 주문서 작성을 위한 DTO 클래스
    * */
    private Long userNo;
    private String userNm;
    private String cellPhone;
    private LocalDate pickupDate;
    private LocalTime pickupTime;
    private String flowertype;
    private String flowercolor;
    private String pricerange;
    private String message;

}
