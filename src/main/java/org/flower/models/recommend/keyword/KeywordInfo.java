package org.flower.models.recommend.keyword;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class KeywordInfo {

    private Long keywordNo;

    private String keywordNm;
}
