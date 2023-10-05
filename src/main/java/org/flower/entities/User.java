package org.flower.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.flower.commons.constants.UserRole;

@Data @Builder          // @Builder : 빌더 패턴을 사용하여 객체를 생성할 수 있게 한다.
@NoArgsConstructor      // 파라미터가 없는 기본 생성자를 생성
@AllArgsConstructor     // 모든 필드를 파라미터로 갖는 생성자를 생성
@Entity                 // 이 클래스가 JPA 엔티티임을 나타낸다. 즉 이 클래스의 인스턴스들은 데이터베이스의 레코드와 매핑된다.
@Table(name="member"    // 'name' 속성으로 해당 엔티티가 매핑될 테이블의 이름을 지정함
        ,indexes = {    // 이메일 및 사용자 역할은 조회가 많이 될 수 있으므로 인덱스 부여
                        // 관리자 페이지에서 최신 회원순으로 조회를 많이 할 수 있으므로 인덱스 부여
        @Index(name = "idx_user_role", columnList = "role"),                // role 컬럼에 대한 인덱스
        @Index(name = "idx_user_createdAt", columnList = "createdAt DESC")  // createdAt 컬럼에 대한 내림차순 인덱스
})
public class User extends BaseEntity{

    /*
    * @ID
    * 'userNo' 필드가 이 엔티티의 기본 키임을 나타낸다.
    *
    * @GeneratedValue(strategy = GenerationType.IDENTITY)
    * 기본키의 값이 데이터베이스에 자동으로 생성되도록 지정한다.
    * */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userNo;            // 회원 번호

    @Column(unique = true, nullable = false, length = 45)
    private String userEmail;       // 아이디(이메일)

    @Column(nullable = false, length = 35)
    private String userNm;          // 회원명

    @Column(nullable = false, length = 65)
    private String userPw;          // 비밀번호

    @Column(length = 11)
    private String cellPhone;       // 전화번호

    @Column(nullable = false, length = 100)
    private String address;          // 주소

    @Column(length = 13)
    private String birth;           // 생년월일

    /*
    * @Enumerated(EnumType.STRING)
    * 열거 타입(enum)인 'role' 필드를 어떻게 데이터 베이스에 저장할지 정의
    * STRING으로 지정하면 enum의 이름이 문자열로 데이터베이스에 저장된다.
    * */
    @Enumerated(EnumType.STRING)
    @Column(length = 30, nullable = false)
    private UserRole role = UserRole.USER;      // 사용자 권한, 기본값은 USER(일반 사용자)
}
