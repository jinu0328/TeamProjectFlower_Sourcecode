package org.flower.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableJpaAuditing
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {

    /**
    *  <메인 페이지> 컨트롤러
    *
    * */
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/")
                .setViewName("front/main/index");
    }
}
