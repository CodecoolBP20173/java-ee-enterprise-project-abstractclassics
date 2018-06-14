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

    private ProfilePageQueries profilePageQueries;
    private UserAccountQueries userAccountQueries;

    public ProfilePageController(ProfilePageQueries profilePageQueries, UserAccountQueries userAccountQueries) {
        this.profilePageQueries = profilePageQueries;
        this.userAccountQueries = userAccountQueries;
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        WebContext context = new WebContext(request, response, request.getServletContext());
        int userId = (Integer) session.getAttribute("userId");

        String[] genders = Stream.of(Gender.values()).map(Gender::name).toArray(String[]::new);
        UserDetail userDetails = new UserDetail();
        userDetails = requestUserDetails(userId, userDetails);

        context.setVariable("genders", genders);
        context.setVariable("userDetails", userDetails);

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        engine.process("profilePage.html", context, response.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int userId = (Integer) session.getAttribute("userId");

        UserDetail userDetail = createUserDetail(request, userId);
        profilePageQueries.updateAccountById(userId, userDetail);

        response.sendRedirect("/profile");
    }

    private UserDetail createUserDetail(HttpServletRequest request, int userId) {
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String phoneNumber = request.getParameter("phonenumber");
        String city = request.getParameter("city");
        String gender = request.getParameter("radioGender");
        Gender genderEnum = Gender.valueOf(gender);
        String imgUrl = request.getParameter("imageInput");
        String intro = request.getParameter("introTextarea");

        String birthDate = request.getParameter("birthday");

        Date parsedBirthDate = getFormatDate(birthDate);

        UserAccount userAccount = userAccountQueries.getUserAccountById(userId);
        UserDetail userDetail = new UserDetail(firstName, lastName, phoneNumber, city,
                parsedBirthDate, genderEnum, intro, userAccount);
        userDetail.setImgUrl(imgUrl);
        return userDetail;
    }

    public static Date getFormatDate(String birthDate) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedBirthDate = Calendar.getInstance().getTime();

        try {
            if (!birthDate.equals("")) parsedBirthDate = format.parse(birthDate);
        } catch (ParseException e) {
            System.err.println("An error has been occured during the birthday' parse!");
        }
        return parsedBirthDate;
    }

    private UserDetail requestUserDetails(int userId, UserDetail userDetails) {
        try {
            userDetails = profilePageQueries.getUserDetailById(userId);
        } catch (NoResultException e) {
            System.err.println("No user's details are found by the given user id!");
        }
        return userDetails;
    }
}
