package com.codecool.labourent.controllers;

import com.codecool.labourent.service.ServiceCategoryService;
import com.codecool.labourent.service.ServiceService;
import com.codecool.labourent.service.UserAccountService;
import com.codecool.labourent.model.Service;
import com.codecool.labourent.model.ServiceCategory;
import com.codecool.labourent.model.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import java.io.IOException;
import java.util.List;

@Controller
public class AddServicePageController {

    @Autowired
    private ServiceCategoryService serviceCategoryService;

    @Autowired
    private ServiceService serviceService;

    @Autowired
    private UserAccountService userAccountService;


    @RequestMapping(value = "/add-service", method = RequestMethod.GET)
    public String profilePageView(Model model) {
        List<ServiceCategory> serviceCategories = serviceCategoryService.getServiceCategories();
        model.addAttribute("serviceCategories", serviceCategories);
        return "addService";
    }
    /*@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        WebContext context = new WebContext(request, response, request.getServletContext());

        List<ServiceCategory> serviceCategories = serviceCategoryService.getServiceCategories();
        context.setVariable("serviceCategories", serviceCategories);
        engine.process("addService.html", context, response.getWriter());
    }*/

    /*@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String serviceName = request.getParameter("name");
        String description = request.getParameter("description");
        double price = Integer.valueOf(request.getParameter("price"));
        int userId = (Integer) session.getAttribute("userId");
        int serviceCategoryId = Integer.valueOf(request.getParameter("serviceCategoryId"));

        UserAccount userAccount = userAccountService.getUserAccountById(userId);
        ServiceCategory serviceCategory = serviceCategoryService.getServiceCategoryById(serviceCategoryId);

        response.sendRedirect("/profile");

        Service service = new Service(serviceName, description, price);
        service.setUserAccount(userAccount);
        service.setServiceCategory(serviceCategory);
        serviceService.saveService(service);
    }*/
}
