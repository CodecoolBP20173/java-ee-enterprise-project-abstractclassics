/*
package com.codecool.labourent.controllers;

import com.codecool.labourent.config.TemplateEngineUtil;
import com.codecool.labourent.service.ServiceCategoryService;
import com.codecool.labourent.service.ServiceService;
import com.codecool.labourent.service.UserAccountService;
import com.codecool.labourent.model.Service;
import com.codecool.labourent.model.ServiceCategory;
import com.codecool.labourent.model.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        WebContext context = new WebContext(request, response, request.getServletContext());

        List<ServiceCategory> serviceCategories = serviceCategoryService.getServiceCategories();
        context.setVariable("serviceCategories", serviceCategories);
        engine.process("addService.html", context, response.getWriter());
    }

    @Override
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
    }
}
*/
