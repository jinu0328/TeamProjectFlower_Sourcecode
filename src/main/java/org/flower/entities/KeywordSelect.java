package org.flower.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString(exclude = {"user", "keyword"})
@EqualsAndHashCode(exclude = {"user","keyword"})
@Table(name="keywordselect")  // 'keywordselect' 속성으로 해당 엔티티가 매핑될 테이블의 이름을 지정
public class KeywordSelect {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long SelectionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userNo")
    private User user;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "keywordNo")
    private Keywords keyword;

    @Column(name = "weight", nullable = false, columnDefinition = "INT DEFAULT 0")
    private int weight;

}
