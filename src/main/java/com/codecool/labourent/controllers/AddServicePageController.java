package com.codecool.labourent.controllers;

import com.codecool.labourent.config.TemplateEngineUtil;
import com.codecool.labourent.dbConnection.ServiceCategoryQueries;
import com.codecool.labourent.dbConnection.ServiceQueries;
import com.codecool.labourent.dbConnection.UserAccountQueries;
import com.codecool.labourent.model.Service;
import com.codecool.labourent.model.ServiceCategory;
import com.codecool.labourent.model.UserAccount;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class AddServicePageController extends HttpServlet {

    private ServiceCategoryQueries serviceCategoryQueries;
    private ServiceQueries serviceQueries;
    private  UserAccountQueries userAccountQueries;


    public AddServicePageController(ServiceCategoryQueries serviceCategoryQueries, ServiceQueries serviceQueries, UserAccountQueries userAccountQueries) {
        this.serviceCategoryQueries = serviceCategoryQueries;
        this.serviceQueries = serviceQueries;
        this.userAccountQueries = userAccountQueries;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        WebContext context = new WebContext(request, response, request.getServletContext());

        List<ServiceCategory> serviceCategories = serviceCategoryQueries.getServiceCategories();
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

        UserAccount userAccount = userAccountQueries.getUserAccountById(userId);
        ServiceCategory serviceCategory = serviceCategoryQueries.getServiceCategoryById(serviceCategoryId);

        response.sendRedirect("/profile");

        Service service = new Service(serviceName, description, price);
        service.setUserAccount(userAccount);
        service.setServiceCategory(serviceCategory);
        serviceQueries.saveService(service);
    }
}
