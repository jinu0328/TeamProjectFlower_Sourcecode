package org.flower.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableJpaAuditing
public class MvcConfig implements WebMvcConfigurer {

    /**
     * 정적 경로 설정
     *
     */
    public void addResourceHandlers(ResourceHandlerRegistry registry){

        // 파일 업로드 경로 설정
    }

    /**
    *  <메인 페이지> 컨트롤러
    *
    * */
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/")
                .setViewName("front/main/index");
    }
}
