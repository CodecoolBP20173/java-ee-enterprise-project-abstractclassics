package com.codecool.labourent.controllers;

import com.codecool.labourent.config.TemplateEngineUtil;
import com.codecool.labourent.service.UserAccountService;
import com.codecool.labourent.model.UserAccount;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class LoginPageController {

    @Autowired
    private UserAccountService userAccountService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        WebContext context = new WebContext(request, response, request.getServletContext());
        engine.process("login.html", context, response.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        handleLogin(request, response, email, password);
    }

    private void handleLogin(HttpServletRequest request, HttpServletResponse response, String email, String password) throws IOException {
        if (passwordIsCorrect(email, password)) {
            UserAccount userAccount = userAccountService.getUserAccountByEmail(email);
            HttpSession session = request.getSession();
            session.setAttribute("email", email);
            session.setAttribute("userId", userAccount.getId());
            response.sendRedirect("/list");
        } else {
            response.sendRedirect("/login?invalid=true");
        }
    }

    private boolean passwordIsCorrect(String email, String plainTextPassword) {
        UserAccount userAccount = userAccountService.getUserAccountByEmail(email);

        if (userAccount == null) {
            return false;
        }

        String hashedPassword = userAccount.getPassword();
        return BCrypt.checkpw(plainTextPassword, hashedPassword);
    }
}
