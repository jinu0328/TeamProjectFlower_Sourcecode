package org.flower.models.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.FlashMapManager;
import org.springframework.web.servlet.support.SessionFlashMapManager;

import java.io.IOException;
import java.util.ResourceBundle;

public class LoginFailureHandler implements AuthenticationFailureHandler {

    private static ResourceBundle bundle = ResourceBundle.getBundle("messages.validation");

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        HttpSession session = request.getSession();

        // spring 에서 제공하는 Flash Attributes를 사용하여 에러메세지를 일회성으로 전달하도록 함
        FlashMap flashMap = new FlashMap();

        String userEmail = request.getParameter("userEmail");
        String userPw = request.getParameter("userPw");

        flashMap.put("userEmail", userEmail);

        if(userEmail == null || userEmail.isBlank()){
            flashMap.put("requiredUserEmail", bundle.getString("NotBlank.email"));
        }

        if(userPw == null || userPw.isBlank()){
            flashMap.put("requiredUserPw", bundle.getString("NotBlank.userPw"));
        }

        if(userEmail != null && !userEmail.isBlank() && userPw != null && !userPw.isBlank()){
            flashMap.put("loginFail", bundle.getString("user.login.fail"));
        }

        FlashMapManager flashMapManager = new SessionFlashMapManager();
        flashMapManager.saveOutputFlashMap(flashMap, request, response);

        String url = request.getContextPath() + "/user/login";
        response.sendRedirect(url);
    }
}
