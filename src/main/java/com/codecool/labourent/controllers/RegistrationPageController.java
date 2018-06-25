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
import java.io.IOException;


@Controller
public class RegistrationPageController {

    @Autowired
    private UserAccountService userAccountService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        WebContext context = new WebContext(request, response, request.getServletContext());
        engine.process("registration.html", context, response.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse servletResponse) throws ServletException, IOException {
        String userName = request.getParameter("username");
        String email = request.getParameter("email");
        String password = hashPassword(request.getParameter("password"));

        handleRegistration(servletResponse, userName, email, password);
    }

    private void handleRegistration(HttpServletResponse servletResponse, String userName, String email, String password) throws IOException {
        if (userAccountService.emailIsTaken(email)) {
            servletResponse.sendRedirect("/registration?taken=email");
        } else if (userAccountService.userNameIsTaken(userName)) {
            servletResponse.sendRedirect("/registration?taken=username");
        } else {
            servletResponse.sendRedirect("/login");
            UserAccount userAccount = new UserAccount(userName, email, password);
            userAccountService.saveUserAccount(userAccount);
        }
    }

    private String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(16));
    }
}
