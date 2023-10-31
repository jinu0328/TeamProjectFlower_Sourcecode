package org.flower.models.order.store;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.flower.entities.StoreImage;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreEditInfo {
    /*
    매장 수정을 위한 DTO 클래스
     */
    private Long storeNo;
    private String storeNm;       // 매장 이름
    private String hours;       // 매장 영업 시간
    private String storeAd;       // 매장 위치
    private String storePh;       // 매장 연락처

}
