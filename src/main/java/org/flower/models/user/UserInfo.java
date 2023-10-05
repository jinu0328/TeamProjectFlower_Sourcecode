package org.flower.models.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/*
 * 스프링 시큐리티 UserDetails 재정의
 * */
@Data          // getter, setter, equals(), hashCode(), toString() 메서드를 지원
@Builder        // Builder 패턴을 사용하여 객체를 생성할 수 있음
                // 이 패턴은 객체의 생성 과정과 표현 방법을 분리하여 동일한 생성 절차서 서로 다른 표현 결과를 만들 수 있게 함
@NoArgsConstructor      // 인자 없는 기본 생성자를 생성
@AllArgsConstructor     // 모든 필드를 인자로 받는 생성자를 생성
public class UserInfo implements UserDetails {

    private Long userNo;            // 회원번호
    private String userEmail;       // 아이디(이메일)
    private String userPw;          // 비밀번호
    private String userPwCk;        // 비밀번호 체크
    private String userNm;          // 회원명
    private String userNickNm;      // 닉네임
    private String cellPhone;       // 전화번호
    private String address;          // 주소

    private Collection<GrantedAuthority> authorities;

    /*
    * 사용자에게 부연된 권한들을 저장하는 컬렉션
    * GrantedAuthority는 Spring Security에서 권한을 표현함
    * */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {   // 사용자의 비밀번호를 반환
        return userPw;
    }

    @Override
    public String getUsername() {   // 사용자의 아이디(이메일)을 반환
        return userEmail;
    }

    @Override
    public boolean isAccountNonExpired() {      // 계정이 만료되지 않았는지를 반환
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {       // 계정이 잠기지 않았는지를 반환
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {  // 사용자의 인증 정보(비밀번호)가 만료되지 않았는지를 반환
        return true;
    }

    @Override
    public boolean isEnabled() {                // 사용자가 활성화 상태인지를 반환
        return true;
    }
}
