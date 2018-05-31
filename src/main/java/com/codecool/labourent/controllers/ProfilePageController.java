package com.codecool.labourent.controllers;


import com.codecool.labourent.config.TemplateEngineUtil;
import com.codecool.labourent.dbConnection.ProfilePageQueries;
import com.codecool.labourent.model.Gender;
import com.codecool.labourent.model.UserAccount;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Stream;


@WebServlet(urlPatterns = {"/profile"})
public class ProfilePageController extends HttpServlet{
    private int userId = 23; //TODO: into session

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        WebContext context = new WebContext(request, response, request.getServletContext());
        String[] genders = Stream.of(Gender.values()).map(Gender::name).toArray(String[]::new);

        UserDetail userDetails = ProfilePageQueries.getUserDetailById(userId);

        context.setVariable("genders", genders);
        context.setVariable("userDetails", userDetails);


        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        engine.process("profilePage.html", context, response.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String phoneNumber = request.getParameter("phonenumber");
        String city = request.getParameter("city");
        String[] birthdayDate = request.getParameterValues("birthday");
        String gender = request.getParameter("radioGender");
        Gender genderEnum = Gender.valueOf(gender);

        String intro = request.getParameter("introTextarea");
        String profileImg = request.getParameter("profileImage");

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String birthDate = request.getParameter("birthday");
        Date parsedBirthDate= new Date();
        try {
            parsedBirthDate = format.parse(birthDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        //TODO: request a UserAccountQueries.getUserAccountById(userId);
        //TODO: create an instance from USerDetail
        /*UserAccount userAccount = UserAccountQueries.getUserAccountById(userId);
        ProfilePageQueries.putUserAccountInDb(firstName,lastName,phoneNumber,city,parsedBirthDate, genderEnum,
                intro,profileImg,userAccount);*/
    }
}
