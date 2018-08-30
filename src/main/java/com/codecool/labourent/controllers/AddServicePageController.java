package com.codecool.labourent.controllers;

import com.codecool.labourent.model.*;
import com.codecool.labourent.service.ServiceCategoryService;
import com.codecool.labourent.service.ServiceService;
import com.codecool.labourent.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AddServicePageController {

    private final ServiceCategoryService serviceCategoryService;

    private final ServiceService serviceService;

    private final UserAccountService userAccountService;

    @Autowired
    public AddServicePageController(ServiceCategoryService serviceCategoryService, ServiceService serviceService, UserAccountService userAccountService) {
        this.serviceCategoryService = serviceCategoryService;
        this.serviceService = serviceService;
        this.userAccountService = userAccountService;
    }

    @RequestMapping(value = "/add-service", method = RequestMethod.GET)
    public String servicePageView(Model model) {
        List<ServiceCategory> serviceCategories = serviceCategoryService.getServiceCategories();
        model.addAttribute("serviceCategories", serviceCategories);
        return "addService";
    }

    @RequestMapping(value = "/add-service", method = RequestMethod.POST)
    public String servicePagePostView(@RequestParam("name") String name, @RequestParam("description") String description,
                                      @RequestParam("price") Double price,
                                      @RequestParam("serviceCategoryId") int serviceCategoryId, HttpServletRequest request) {

        HttpSession session = request.getSession();
        int userId = (Integer) session.getAttribute("userId");

        UserAccount userAccount = userAccountService.getUserAccountById(userId);
        ServiceCategory serviceCategory = serviceCategoryService.getServiceCategoryById(serviceCategoryId);

        Service service = new Service(name, description, price);
        service.setUserAccount(userAccount);
        service.setServiceCategory(serviceCategory);
        serviceService.saveService(service);

        return "redirect:profile";
    }
}
