package org.flower.commons.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Arrays;

/**
* 공통 인터셉터
 * HTTP 요청이 컨트롤러로 전달되기 전에 특정 로직을 실행하는 데 사용
* */
@Component
public class CommonInterceptor implements HandlerInterceptor {

    // 컨트롤러로 요청이 전달되기 전에 실행됨
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws  Exception{
        // bodyClass 문자열 값을 요청(request)의 속성으로 설정
        // 이후 후행되는 컨트롤러나 뷰에서 bodyClass 속성을 참조하여 사용함
        String bodyClass = getBodyClass(request);
        return true;
    }

    /*
    * getBodyClass 메서드
    * HTTP 요청의 URI를 분석하여 어떤 CSS클래스를 HTML 'body'태그에 동적으로 할당할 것인 결정하는 문자열을 반환하는 로직을 담고 있음
    * 이렇게 하면 URI 경로에 따라 다르게 스타일링을 할 수 있다. (js, img도 포함)
    * */
    private String getBodyClass(HttpServletRequest request) {
        // HTTP 요청 객체에서 URI를 추출
        String URI = request.getRequestURI();
        String bodyClass = "body-main";

        // ex) style.css, style2.css, style3.css 파일을 만들어도 적용되게끔 함
        if(URI.indexOf(".") == -1){
            String[] classes = Arrays.stream(URI.split("/")).filter(s -> !s.isBlank() && s.indexOf(".") == -1).toArray(String[]::new);

            if(classes.length > 0){
                bodyClass = "body-" + classes[0];
                if(classes.length > 1) bodyClass += " body-" + classes[0] + "-" + classes[1];
            }

            return bodyClass;
        }
        return bodyClass;
    }
}
