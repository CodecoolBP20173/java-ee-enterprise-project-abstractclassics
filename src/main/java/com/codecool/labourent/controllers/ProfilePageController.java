package com.codecool.labourent.controllers;

import com.codecool.labourent.service.UserAccountService;
import com.codecool.labourent.service.UserDetailService;
import com.codecool.labourent.model.Gender;
import com.codecool.labourent.model.UserAccount;
import com.codecool.labourent.model.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

@Controller
public class ProfilePageController {

    @Autowired
    private UserDetailService userDetailService;

    @Autowired
    private UserAccountService userAccountService;

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String profilePageView(Model model) {
        //int userId = (Integer) session.getAttribute("userId");
        int userId = 1;
        String[] genders = Stream.of(Gender.values()).map(Gender::name).toArray(String[]::new);

        UserDetail userDetail = userDetailService.getUserDetailById(userId);

        model.addAttribute("genders", genders);
        model.addAttribute("userDetails", userDetail);

        return "profilePage";
    }
    
    /*@Override
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
    }*/

    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    public String profilePagePostView(Model model, @RequestParam("firstname") String firstName, @RequestParam("lastname") String lastName,
                                      @RequestParam("phonenumber") String phoneNumber, @RequestParam("city") String city,
                                      @RequestParam("radioGender") String radioGender, @RequestParam("imageInput") String imageInput,
                                      @RequestParam("introTextarea") String introTextarea, @RequestParam("birthday") String birthday) {
        //int userId = (Integer) session.getAttribute("userId");
        int userId = 1;

        Gender genderEnum = Gender.valueOf(radioGender);

        Date parsedBirthDate = getFormatDate(birthday);

        UserAccount userAccount = userAccountService.getUserAccountById(userId);
        UserDetail userDetail = new UserDetail(firstName, lastName, phoneNumber, city,
                parsedBirthDate, genderEnum, introTextarea, userAccount);
        userDetail.setImgUrl(imageInput);

        //TODO: here the userdetail has already saved
        userDetailService.saveUserDetail(userDetail);

    }
    /*
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int userId = (Integer) session.getAttribute("userId");

        UserDetail userDetail = createUserDetail(request, userId);
        if (userDetailService.isUserAccountExist(userId)) {
            userDetailService.updateAccountById(userId, userDetail);
        } else {
            userDetailService.putUserAccountInDb(userDetail);
        }

        response.sendRedirect("/profile");
    }*/



    /*private UserDetail createUserDetail(HttpServletRequest request, int userId) {
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

        UserAccount userAccount = userAccountService.getUserAccountById(userId);
        UserDetail userDetail = new UserDetail(firstName, lastName, phoneNumber, city,
                parsedBirthDate, genderEnum, intro, userAccount);
        userDetail.setImgUrl(imgUrl);
        return userDetail;
    }



    private UserDetail requestUserDetails(int userId, UserDetail userDetails) {
        try {
            userDetails = userDetailService.getUserDetailById(userId);
        } catch (NoResultException e) {
            System.err.println("No user's details are found by the given user id!");
        }
        return userDetails;
    }*/

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
}
