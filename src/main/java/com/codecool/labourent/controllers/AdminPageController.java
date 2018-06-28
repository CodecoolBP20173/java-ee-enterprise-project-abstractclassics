package com.codecool.labourent.controllers;

import com.codecool.labourent.service.ServiceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminPageController {

    @Autowired
    ServiceCategoryService serviceCategoryService;

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPageView(Model model) {

        return "adminPage";
    }
}
