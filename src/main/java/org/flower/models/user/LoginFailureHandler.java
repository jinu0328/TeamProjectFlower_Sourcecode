package org.flower.models.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.NotBlank;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;
import java.util.ResourceBundle;


public class LoginFailureHandler implements AuthenticationFailureHandler {

    private static ResourceBundle bundle = ResourceBundle.getBundle("messages.validation");
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        HttpSession session = request.getSession();
        session.removeAttribute("requiredUserEmail");
        session.removeAttribute("requiredUserPw");
        session.removeAttribute("loginFail");
        session.removeAttribute("userEmail");

        String userEmail = request.getParameter("userEmail");
        String userPw = request.getParameter("userPw");

        session.setAttribute("userEmail", userEmail);

        if(userEmail == null || userEmail.isBlank()){
            session.setAttribute("requiredUserEmail", bundle.getString("NotBlank.emil"));
        }

        if(userPw == null || userPw.isBlank()){
            session.setAttribute("requiredUserPw", bundle.getString("NotBlank.userPw"));
        }

        if(userEmail != null && !userEmail.isBlank() && userPw !=null && !userPw.isBlank()){
            session.setAttribute("loginFail", bundle.getString("user.login.fail"));
        }

        String url = request.getContextPath() + "/user/login";
        response.sendRedirect(url);
    }
}
