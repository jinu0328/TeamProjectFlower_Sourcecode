package org.flower.models.recommend.weight;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.flower.entities.Flower;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WeightInfo {

    private Long id;

    private Long flowerNo;

    private Long keywordNo;

    private String flowerNm;
    private String keywordNm;
}
