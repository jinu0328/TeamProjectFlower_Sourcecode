package org.flower.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .anyRequest().permitAll();

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
