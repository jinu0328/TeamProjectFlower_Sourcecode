package org.flower.configs;

import org.flower.commons.interceptors.CommonInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@EnableJpaAuditing
public class MvcConfig implements WebMvcConfigurer {

    @Value("C:/uploads/")
    private String fileUploadPath;

    @Autowired
    private CommonInterceptor commonInterceptor;

    /**
     * 정적 경로 설정!
     *
     */
    public void addResourceHandlers(ResourceHandlerRegistry registry){

        // 파일 업로드 경로 설정
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:///" + fileUploadPath);
    }

    /**
    * 메세지 설정
    *
    * @desc
     *      - messages.commons : 공통 메세지
     *      - messages.errors : 에러 관련 메세지
     *      - messages.validation : 유효성 검사 관련 페이지
    * */

    @Bean
    public MessageSource messageSource(){
        // 메세지 소스파일(.properties)을 읽어 메세지를 제공
        ResourceBundleMessageSource ms = new ResourceBundleMessageSource();
        ms.setDefaultEncoding("UTF-8");
        ms.setBasenames("messages.commons", "messages.errors", "messages.validation");
        return ms;
    }

    /**
    *  <메인 페이지> 컨트롤러
    * @param registry
    * */
    @Override
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/")
                .setViewName("front/main/index");
    }

    public void addInterceptors(InterceptorRegistry registry){
        // 공통 인터셉터
        registry.addInterceptor(commonInterceptor)
                .addPathPatterns("/**");
    }
}
