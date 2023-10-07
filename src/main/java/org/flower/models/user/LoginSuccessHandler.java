package org.flower.models.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

/*
* 사용자가 성공적으로 로그인 한 후에 실행되는 로직을 정의하는 인터페이스.
* */
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    // 클래스 선언부에서 AuthenticationSuccessHandler 인터페이스를 구현함으로 로그인 성공 핸들러의 역할을 함

    // onAuthenticationSucesss 메서드는 사용자가 성공적으로 로그인했을 때 호출됨.
    // 이 메서드는 HTTP 요청, 응답 그리고 인증 객체를 파라미터로 받는다.
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // HTTP 요청으로부터 세션을 가져옴
        // 세션은 사용자별로 서버에 저장되는 정보를 가지고 있으며, 여러 요청에서 상태를 유지하기 위해 사용됨
        HttpSession session = request.getSession();
        // 아래 요소와 같은 데이터를 세션에서 제거하는 역할
        session.removeAttribute("requiredUserEmail");
        session.removeAttribute("requiredUserPw");
        session.removeAttribute("loginFail");
        session.removeAttribute("userEmail");

        /*
        * authentication.getPrincipal()
        * 인증 객체에서 주체(principal) 정보를 가져옴 여기서 주체 정보는 UserInfo 이다.
        * */
        UserInfo userInfo = (UserInfo)authentication.getPrincipal();
        session.setAttribute("userInfo", userInfo);

        /*
        * request.getContextPath()
        * 이 메서드는 웹 어플리케이션의 컨텍스트 경로를 반환
        *
        * response.sendRedircect(url)
        * 사용자의 브라우저에 주어진 URL로 리다이렉트하라는 지시를 보냄
        * */
        String url = request.getContextPath();
        response.sendRedirect(url);
    }
}
