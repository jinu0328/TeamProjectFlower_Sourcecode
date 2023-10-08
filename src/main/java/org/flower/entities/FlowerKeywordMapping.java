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
}
