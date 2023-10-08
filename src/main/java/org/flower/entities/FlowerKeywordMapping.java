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
    private Long id;

    @ManyToOne
    private Flower flower;

    @ManyToOne
    private Keywords keyword;

    private Integer weight;
}
