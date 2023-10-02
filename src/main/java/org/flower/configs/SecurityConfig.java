package org.flower.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;

@Configuration
public class SecurityConfig {

    /* 이부분도 좀 더 봐야 할 것 같음,, 
    @Override
    public void configure(WebSecurity web) {
        web
                .ignoring() // spring security 필터 타지 않도록 설정
                .antMatchers("/resources/**") // 정적 리소스 무시
                .antMatchers("/h2-console/**"); // h2-console 무시
    }
    */


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
