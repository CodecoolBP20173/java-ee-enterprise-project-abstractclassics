package com.codecool.labourent.controllers;

import com.codecool.labourent.config.TemplateEngineUtil;
import com.codecool.labourent.dbConnection.UserAccountQueries;
import com.codecool.labourent.model.UserAccount;
import org.mindrot.jbcrypt.BCrypt;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/login"})
public class LoginPageController extends HttpServlet{
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

        if (passwordIsCorrect(email, password)) {
            response.sendRedirect("/list");
        } else {
            response.sendRedirect("/login?invalid=true");
        }
    }

    private boolean passwordIsCorrect(String email, String plainTextPassword) {
        UserAccount userAccount = UserAccountQueries.getUserAccountByEmail(email);

        if (userAccount == null) {
            return false;
        }

        String hashedPassword = userAccount.getPassword();
        return BCrypt.checkpw(plainTextPassword, hashedPassword);
    }
}
