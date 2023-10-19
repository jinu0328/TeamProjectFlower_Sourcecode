package org.flower.models.recommend.flower;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor @AllArgsConstructor
public class FlowerInfo {
    private Long flowerNo;            // 꽃 식별자

    private String flowerNm;       // 꽃 이름

    private String flowerMean;          // 꽃말

    private String bloomseason;          // 개화 시기(월별)

    private String season;               // 봄, 여름, 가을, 겨울

    private String flowerIamges;   // 꽃 추천 시 뜨는 이미지

    private int likes; // 좋아용


}
