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
@Table(name="keywords")    // 'name' 속성으로 해당 엔티티가 매핑될 테이블의 이름을 지정함
public class Keywords extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long keywordNo;            // 키워드 식별자

    @Column(nullable = false, length = 10)
    private String keywordNm;       // 키워드 이름


}




