package com.codecool.labourent.controllers;


import com.codecool.labourent.config.TemplateEngineUtil;
import com.codecool.labourent.model.Gender;
import com.codecool.labourent.model.UserDetail;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

@WebServlet(urlPatterns = {"/profile"})
public class ProfilePageController extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //System.err.println("I'm on the user profile page");
        /*EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpaexamplePU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        UserDetail userDetail = new UserDetail();*/
        String[] genders = Stream.of(Gender.values()).map(Gender::name).toArray(String[]::new);

        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("userId", 1);
        context.setVariable("genders", genders);


        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        engine.process("profile.html", context, resp.getWriter());
        //entityManager.close();
        //entityManagerFactory.close();

    }
}
