package com.codecool.labourent.controllers;


import com.codecool.labourent.config.TemplateEngineUtil;
import com.codecool.labourent.dbConnection.ProfilePageQueries;
import com.codecool.labourent.dbConnection.UserAccountQueries;
import com.codecool.labourent.model.Gender;
import com.codecool.labourent.model.UserAccount;
import com.codecool.labourent.model.UserDetail;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.persistence.NoResultException;
import javax.servlet.ServletException;
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


public class ProfilePageController extends HttpServlet {
    String profileImg = "/static/img/default_profile.png";

    private ProfilePageQueries profilePageQueries;
    private UserAccountQueries userAccountQueries;

    public ProfilePageController(ProfilePageQueries profilePageQueries, UserAccountQueries userAccountQueries) {
        this.profilePageQueries = profilePageQueries;
        this.userAccountQueries = userAccountQueries;
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        if (session.getAttribute("userId") == null) {
            response.sendRedirect("/login");

        } else {
            WebContext context = new WebContext(request, response, request.getServletContext());
            int userId = (Integer) request.getSession().getAttribute("userId");

            String[] genders = Stream.of(Gender.values()).map(Gender::name).toArray(String[]::new);
            UserDetail userDetails;

            try {
                userDetails = profilePageQueries.getUserDetailById(userId);
            } catch (NoResultException e) {
                userDetails = new UserDetail();
                System.err.println("No user's details are found by the given user id!");
            }

            context.setVariable("genders", genders);
            context.setVariable("userDetails", userDetails);


            TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
            engine.process("profilePage.html", context, response.getWriter());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        if (session.getAttribute("userId") == null) {
            response.sendRedirect("/login");

        } else {
            int userId = (Integer) session.getAttribute("userId");
            String firstName = request.getParameter("firstname");
            String lastName = request.getParameter("lastname");
            String phoneNumber = request.getParameter("phonenumber");
            String city = request.getParameter("city");
            String gender = request.getParameter("radioGender");
            Gender genderEnum = Gender.valueOf(gender);

            String intro = request.getParameter("introTextarea");
            //String profileImg = request.getParameter("profileImage");

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedBirthDate = Calendar.getInstance().getTime();
            String birthDate = "";

            try {
                birthDate = request.getParameter("birthday");
                if (!birthDate.equals("")) parsedBirthDate = format.parse(birthDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            UserAccount userAccount = userAccountQueries.getUserAccountById(userId);

            if (profilePageQueries.isUserAccountExsist(userId)) {
                profilePageQueries.updateAccountById(userId, firstName, lastName, phoneNumber, city, parsedBirthDate, genderEnum,
                        intro, profileImg);
            } else {
                profilePageQueries.putUserAccountInDb(firstName, lastName, phoneNumber, city, parsedBirthDate, genderEnum,
                        intro, profileImg, userAccount);
            }

            response.sendRedirect("/profile");
        }
    }
}
