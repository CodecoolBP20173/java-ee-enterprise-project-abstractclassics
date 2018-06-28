
package com.codecool.labourent.controllers;

import com.codecool.labourent.service.UserAccountService;
import com.codecool.labourent.service.UserDetailService;
import com.codecool.labourent.model.Gender;
import com.codecool.labourent.model.UserAccount;
import com.codecool.labourent.model.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
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
    public String profilePageView(Model model, HttpServletRequest request) {

        //TODO:int userId = (Integer) session.getAttribute("userId");
        HttpSession session = request.getSession();
        int userId = (Integer) session.getAttribute("userId");
        //int userId = 1;
        String[] genders = Stream.of(Gender.values()).map(Gender::name).toArray(String[]::new);

        UserDetail userDetail = userDetailService.getUserDetailById(userId);

        model.addAttribute("genders", genders);
        model.addAttribute("userDetails", userDetail);

        return "profilePage";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    public String profilePagePostView(Model model, @RequestParam("firstname") String firstName, @RequestParam("lastname") String lastName,
                                      @RequestParam("phonenumber") String phoneNumber, @RequestParam("city") String city,
                                      @RequestParam("radioGender") String radioGender, @RequestParam("imageInput") String imageInput,
                                      @RequestParam("introTextarea") String introTextarea, @RequestParam("birthday") String birthday,
                                      HttpServletRequest request) {

        HttpSession session = request.getSession();
        int userId = (Integer) session.getAttribute("userId");


        Gender genderEnum = Gender.valueOf(radioGender);

        Date parsedBirthDate = getFormatDate(birthday);

        UserAccount userAccount = userAccountService.getUserAccountById(userId);
        UserDetail userDetail = new UserDetail(firstName, lastName, phoneNumber, city,
                parsedBirthDate, genderEnum, introTextarea, userAccount);
        userDetail.setImgUrl(imageInput);

        if (userDetailService.isUserAccountExist(userId)) {
            userDetailService.updateAccountById(userId, userDetail);
        } else {
            userDetailService.putUserAccountInDb(userDetail);
        }


        return "redirect:profile";
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
}

