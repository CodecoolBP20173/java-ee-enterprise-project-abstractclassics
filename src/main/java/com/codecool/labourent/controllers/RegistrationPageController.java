package com.codecool.labourent.controllers;

import com.codecool.labourent.model.UserAccount;
import com.codecool.labourent.model.UserDetail;
import com.codecool.labourent.service.UserAccountService;
import com.codecool.labourent.service.UserDetailService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import java.io.IOException;


@Controller
public class RegistrationPageController {

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private UserDetailService userDetailService;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String showRegistrationForm() {
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registerUserAccount(WebRequest request) throws IOException {
        String userName = request.getParameter("username");
        String email = request.getParameter("email");
        String password = hashPassword(request.getParameter("password"));

        return handleRegistration(userName, email, password);
    }

    private String handleRegistration(String userName, String email, String password) throws IOException {
        if (userAccountService.emailIsTaken(email)) {
            return "redirect:" + "/registration?taken=email";
        } else if (userAccountService.userNameIsTaken(userName)) {
            return "redirect:" + "/registration?taken=username";
        } else {
            UserAccount userAccount = new UserAccount(userName, email, password);
            userAccountService.saveUserAccount(userAccount);
            UserDetail userDetail = new UserDetail(userAccount);
            userDetailService.saveUserDetail(userDetail);
            return "redirect:" + "/login";
        }
    }

    private String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(16));
    }
}
