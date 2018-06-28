package com.codecool.labourent.config;

import com.codecool.labourent.model.UserAccount;
import com.codecool.labourent.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final UserAccountService userAccountService;

    @Autowired
    public CustomAuthenticationSuccessHandler(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String email = authentication.getName();
        String role = getRoleAsString(authentication);
        UserAccount userAccount = userAccountService.getUserAccountByEmail(email);

        HttpSession session = request.getSession();
        session.setAttribute("email", email);
        session.setAttribute("role", role);

        if (IsAdmin(authentication)) {
            session.setAttribute("userId", 0);
        } else {
            session.setAttribute("userId", userAccount.getId());
        }

        response.sendRedirect("/list");
    }

    private String getRoleAsString(Authentication authentication) {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Object[] authoritiesArray = authorities.toArray();
        GrantedAuthority authority = (GrantedAuthority) authoritiesArray[0];
        String role = authority.getAuthority().replace("ROLE_", "");
        return role;
    }

    private boolean IsAdmin(Authentication authentication) {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        return authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }
}
