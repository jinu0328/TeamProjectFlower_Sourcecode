package org.flower.models.order.store;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreInfo {

    private Long storeNo;
    private Long userNo;
    private String userNm;
    private String userNickNm;
    private String cellPhone;

    private String storeNm;
    private String hours;
    private String storeAd;
    private String storePh;
    private String storeImages;
}
