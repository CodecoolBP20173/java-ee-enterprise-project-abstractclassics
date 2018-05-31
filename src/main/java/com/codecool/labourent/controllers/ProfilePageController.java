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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.stream.Stream;


@WebServlet(urlPatterns = {"/profile"})
public class ProfilePageController extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //TODO:remove it
        HttpSession session = request.getSession();
        session.setAttribute("userId", 6);


        WebContext context = new WebContext(request, response, request.getServletContext());
        int userId = (Integer) request.getSession().getAttribute("userId");

        String[] genders = Stream.of(Gender.values()).map(Gender::name).toArray(String[]::new);
        UserDetail userDetails = new UserDetail();

        try {
            userDetails = ProfilePageQueries.getUserDetailById(userId);
        } catch (NoResultException e) {
            System.err.println("No user's details are found by the given user id!");
        }

        context.setVariable("genders", genders);
        context.setVariable("userDetails", userDetails);


        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        engine.process("profilePage.html", context, response.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = (Integer) request.getSession().getAttribute("userId");
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
        Date parsedBirthDate  = Calendar.getInstance().getTime();
        String birthDate = "";

        try {
            birthDate = request.getParameter("birthday");
            if (!birthDate.equals("")) parsedBirthDate = format.parse(birthDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        //TODO: request a UserAccountQueries.getUserAccountById(userId);
        //TODO: create an instance from USerDetail
        UserAccount userAccount = getUserAccountById(userId);//UserAccountQueries.getUserAccountById(userId);

        if (ProfilePageQueries.isUserAccountExsist(userId)) {
            ProfilePageQueries.updateAccountById(userId,firstName,lastName,phoneNumber,city,parsedBirthDate, genderEnum,
                    intro,profileImg);
            System.err.println(userId + " is exsist");
        } else {
            ProfilePageQueries.putUserAccountInDb(firstName,lastName,phoneNumber,city,parsedBirthDate, genderEnum,
                    intro,profileImg,userAccount);
            System.err.println(userId + " is not exsist");
        }

        /*ProfilePageQueries.putUserAccountInDb(firstName,lastName,phoneNumber,city,parsedBirthDate, genderEnum,
                intro,profileImg,userAccount);*/
    }

    private UserAccount getUserAccountById(int userID) {
        UserAccount userAccount = new UserAccount();
        userAccount.setId(userID);
        userAccount.setEmail("sss");
        userAccount.setPassword("sss");
        userAccount.setUserName("sssssss");

        return userAccount;
    }
}
