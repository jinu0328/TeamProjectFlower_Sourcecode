package org.flower.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.flower.commons.constants.UserRole;

@Data @Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="member"
        ,indexes = {    // 이메일 및 사용자 역할은 조회가 많이 될 수 있으므로 인덱스 부여
                        // 관리자 페이지에서 최신 회원순으로 조회를 많이 할 수 있으므로 인덱스 부여
        @Index(name = "idx_user_role", columnList = "role"),
        @Index(name = "idx_user_createdAt", columnList = "createdAt DESC")
})
public class User extends BaseEntity{

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
    private String adress;          // 주소

    @Column(length = 13)
    private String birth;           // 생년월일

    @Enumerated(EnumType.STRING)
    @Column(length = 30, nullable = false)
    private UserRole role = UserRole.USER;      // 사용자 권한, 기본값은 USER(일반 사용자)
}
