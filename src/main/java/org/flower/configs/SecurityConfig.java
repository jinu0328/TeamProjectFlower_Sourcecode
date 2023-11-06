package org.flower.configs;

import org.flower.models.user.LoginFailureHandler;
import org.flower.models.user.LoginSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {

    /*
     * SecurityFilterChain은 Spring Security의 보안 체인을 정의함.
     * 보안 체인은 필터의 체으로 HTTP 요청이 어플리케이션에 도달하기 전에 거쳐가는 여러 보안 매커니즘을 담당
     * */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http.formLogin()
                .loginPage("/user/login")
                .successHandler(new LoginSuccessHandler())
                .usernameParameter("userEmail")
                .passwordParameter("userPw")
                .failureHandler(new LoginFailureHandler());

        http.logout()
                .logoutUrl("/user/logout")          // 로그아웃 요청 URL 지정
                .logoutSuccessUrl("/")              // 로그아웃 성공 후 리다이렉트 될 URL 지정
                .invalidateHttpSession(true)        // 로그아웃 시 HTTP 세션 무효화 여부
                .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
                .deleteCookies("JSESSIONID");

        http.authorizeHttpRequests()
                .anyRequest().permitAll();

        //http.csrf().disable();

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

    /*
    * PasswordEncoder 빈 생성
    * 이 인터페이스는 Spring Security에서 제공하며, 사용자의 비밀번호를 안전하게 인코딩 하는 데 사용
    *
    * BCryptPasswordEncoder
    * BCrypt 해시 함수를 사용해서 비밀번호를 인코딩함. BCrypt는 솔트를 사용하여 해시를 생성하고, 솔트가 자동으로
    * 해시와 함께 저장되므로 매번 다른 해시 값을 생성하여 보안성을 높인다.
    * */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
