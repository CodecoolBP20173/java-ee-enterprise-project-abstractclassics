package com.codecool.labourent.controllers;

import com.codecool.labourent.model.ServiceCategory;
import com.codecool.labourent.service.ServiceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class AdminPageController {

    @Autowired
    ServiceCategoryService serviceCategoryService;

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPageView(Model model) {
        List<ServiceCategory> serviceCategories = serviceCategoryService.getServiceCategories();
        model.addAttribute("serviceCategories", serviceCategories);
        return "adminPage";
    }
}
