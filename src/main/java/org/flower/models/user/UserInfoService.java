package org.flower.models.user;

import org.flower.entities.User;
import org.flower.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service        // 스프링이 이 클래스를 서비스로 인식하고 관리하도록 하는 어노테이션
public class UserInfoService implements UserDetailsService {
    /*
    * implements UserDetailService
    * UserDetailService 인터페이스를 구현, 이 인터페이스의 loadUserByUsername 메서드를 오버라이드하여 사용자의 정보를 불러옴
    * */

    @Autowired      // 스프링의 의존섭 주입 기능을 사용하여 UserRepository 타입의 빈을 자동으로 주입
    private UserRepository repository;  // 사용자 정보를 데이터 소스에서 조회하는 데 사용

    /*
    * loadUserByUsername()
    * UserDetailService 인터페이스의 메서드로, 주어진 사용자명으로 사용자 정보를 조회하고 UserDetails 객체로 반환
    * */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUserEmail(username);       // username 을 매개변수로 받아, 해당 사용자의 정보를 데이터 소스에서 조회

        if(user == null){       // 조회된 상용자 정보가 null인 경우, UsernameNotFoundException 발생
            throw new UsernameNotFoundException(username);
        }

        /*
        * 권한 정보 생성
        * 권한 정보를 GrantedAuthrity 타입의 리스트로 생성.
        * SimpleGrantedAuthrity를 사용하여 사용자의 역할 정보 (user.getRole())를 GrantedAuthority로 변환
        * */
        List<GrantedAuthority> authorities= Arrays.asList(new SimpleGrantedAuthority(user.getRole().toString()));

        /*
        * UserInfo 클래스의 빌더를 사용하여 UserDetails 객체를 생성하고 반환.
        * user 객체에서 추출한 정보를 UserInfo 객체의 필드에 할당한다.
        * */
        return UserInfo.builder()
                .userNo(user.getUserNo())
                .userEmail(user.getUserEmail())
                .userNm(user.getUserNm())
                .userPw(user.getUserPw())
                .userNickNm(user.getUserNickNm())
                .cellPhone(user.getCellPhone())
                .authorities(authorities)
                .build();
    }

    /*
    * UserInfoService는 사용자 인증 시 사용자명을 기반으로 사용자 정보를 데이터 소스에서 조회하고,
    * 이를 Spring Security에서 사용할 수 있는 UserDetails 타입으로 반환하여 인증과 권환 확인 과정에서 활용됨
    * */
}
