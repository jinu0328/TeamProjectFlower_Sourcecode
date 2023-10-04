package org.flower.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    /*
     * SecurityFilterChain은 Spring Security의 보안 체인을 정의함.
     * 보안 체인은 필터의 체으로 HTTP 요청이 어플리케이션에 도달하기 전에 거쳐가는 여러 보안 매커니즘을 담당
     * */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http.authorizeRequests()
                .anyRequest().permitAll();
        /*
        * authorizeRequests() -> HttpServletRequest에 대한 액세스 제한을 설정
        * anyRequest() -> 모든 요청을 선택하는 메서드 , permitAll() -> 선택된 요청에 대한 접근을 허용
        * */

        // build() 메소드를 호출하여 HttpSecurity 객체를 빌드하고, 이를 사용하여 SecurityFilterChain 객체를 생성 및 반환
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return w -> w.ignoring() // 시큐리티가 무시할 정적 경로 설정
                .requestMatchers(
                        "/front/images/**",
                        "/mobile/images/**",
                        "/admin/images/**",
                        "/front/js/**",
                        "/mobile/js/**",
                        "/admin/js/**",
                        "/front/css/**",
                        "/mobile/css/**",
                        "/admin/css/**",
                        "/uploads/**");
    }

}
