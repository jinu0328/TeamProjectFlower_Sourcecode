package org.flower.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;
import org.flower.commons.constants.UserRole;

@Data @Builder          // @Builder : 빌더 패턴을 사용하여 객체를 생성할 수 있게 한다.
@NoArgsConstructor      // 파라미터가 없는 기본 생성자를 생성
@AllArgsConstructor     // 모든 필드를 파라미터로 갖는 생성자를 생성
@Entity                 // 이 클래스가 JPA 엔티티임을 나타낸다. 즉 이 클래스의 인스턴스들은 데이터베이스의 레코드와 매핑된다.
@Table(name="flowerinfo"    // 'name' 속성으로 해당 엔티티가 매핑될 테이블의 이름을 지정함
        ,indexes = {    // 이메일 및 사용자 역할은 조회가 많이 될 수 있으므로 인덱스 부여
        // 관리자 페이지에서 최신 회원순으로 조회를 많이 할 수 있으므로 인덱스 부여
        @Index(name = "idx_user_createdAt", columnList = "createdAt DESC")  // createdAt 컬럼에 대한 내림차순 인덱스
})
public class Flower extends BaseEntity{
    /*
     * @ID
     * 'flowerNo' 필드가 이 엔티티의 기본 키임을 나타낸다.
     *
     * @GeneratedValue(strategy = GenerationType.IDENTITY)
     * 기본키의 값이 데이터베이스에 자동으로 생성되도록 지정한다.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long flowerNo;            // 꽃 식별자

    @Column(nullable = false, length = 10)
    private String flowerNm;       // 꽃 이름

    @Column(nullable = false, length = 255)
    private String flowerMean;          // 꽃말

    @Column(length = 65)
    private String bloomseason;          // 개화 시기(월별)

    @Column(length = 10)
    private String season;               // 봄, 여름, 가을, 겨울

    @OneToMany(mappedBy = "flower", cascade = CascadeType.ALL)
    private Set<FlowerImage> flowerIamges;

}




