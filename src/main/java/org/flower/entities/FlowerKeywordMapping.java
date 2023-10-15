package org.flower.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString(exclude = {"flower", "keyword"})
@EqualsAndHashCode(exclude = {"flower", "keyword"})
@Table(name="mapping") // 'mapping' 속성으로 해당 엔티티가 매핑될 테이블의 이름을 지정
public class FlowerKeywordMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne // 다대일 관계
    @JoinColumn(name = "flowerNo", referencedColumnName = "flowerNo", nullable = false)
    // @JoinColumn으로 연관관계를 관리하는 테이블의 컬럼 이름을 명시
    // referencedColumnName은 참조하고 있는 다른 엔티티의 컬럼 이름을 명시
    private Flower flower;

    @ManyToOne
    @JoinColumn(name = "keywordNo", referencedColumnName = "keywordNo", nullable = false)
    private Keywords keyword;

    private Integer weight;

    // 꽃 이름과 키워드 이름을 저장할 새로운 필드를 추가합니다.
    @Transient // 이 필드들이 데이터베이스에 저장되지 않도록 @Transient를 사용합니다.
    private String flowerNm;

    @Transient
    private String keywordNm;

    // 새로운 필드에 대한 getter 및 setter 메서드를 생성합니다.
    public String getFlowerNm() {
        return flower != null ? flower.getFlowerNm() : null;
    }

    public void setFlowerNm(String flowerNm) {
        this.flowerNm = flowerNm;
    }

    public String getKeywordNm() {
        return keyword != null ? keyword.getKeywordNm() : null;
    }

    public void setKeywordNm(String keywordNm) {
        this.keywordNm = keywordNm;
    }
}

