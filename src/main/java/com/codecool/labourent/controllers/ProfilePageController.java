package com.codecool.labourent.controllers;


import com.codecool.labourent.config.EntityManagerSingleton;
import com.codecool.labourent.config.TemplateEngineUtil;
import com.codecool.labourent.model.Gender;
import com.codecool.labourent.model.UserDetail;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.persistence.*;
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

        WebContext context = new WebContext(req, resp, req.getServletContext());
        String[] genders = Stream.of(Gender.values()).map(Gender::name).toArray(String[]::new);
        int userId = 1;
        UserDetail details = new UserDetail();

        EntityManager entityManager = EntityManagerSingleton.getInstance();

        try {
            TypedQuery<UserDetail> queryUserDetail =
                    entityManager.createQuery("SELECT c FROM UserDetail c WHERE c.id = :userId", UserDetail.class);
            details = queryUserDetail.setParameter("userId", userId).getSingleResult();

            System.err.println(queryUserDetail);
        } catch (NoResultException e) {

        }

        context.setVariable("genders", genders);
        context.setVariable("userDetails", details);

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        engine.process("profile.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpaexamplePU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.close();
        entityManagerFactory.close();
    }
}
