package com.codecool.labourent.config;

import com.codecool.labourent.model.UserAccount;
import com.codecool.labourent.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    UserAccountService userAccountService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String email = authentication.getName();
        UserAccount userAccount = userAccountService.getUserAccountByEmail(email);
        HttpSession session = request.getSession();
        session.setAttribute("email", email);
        session.setAttribute("userId", userAccount.getId());
        response.sendRedirect("/list");
    }
}
