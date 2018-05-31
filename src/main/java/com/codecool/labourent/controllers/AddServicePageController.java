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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/add-service"})
public class AddServicePageController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        if (session.getAttribute("userId") == null) {
            response.sendRedirect("/login");

        } else {
            TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
            WebContext context = new WebContext(request, response, request.getServletContext());

            List<ServiceCategory> serviceCategories = ServiceCategoryQueries.getServiceCategories();
            context.setVariable("serviceCategories", serviceCategories);
            engine.process("addService.html", context, response.getWriter());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        if (session.getAttribute("userId") == null) {
            response.sendRedirect("/login");

        } else {
            String serviceName = request.getParameter("name");
            String description = request.getParameter("description");
            double price = Integer.valueOf(request.getParameter("price"));
            int userId = (Integer) session.getAttribute("userId");
            int serviceCategoryId = Integer.valueOf(request.getParameter("serviceCategoryId"));

            UserAccount userAccount = UserAccountQueries.getUserAccountById(userId);
            ServiceCategory serviceCategory = ServiceCategoryQueries.getServiceCategoryById(serviceCategoryId);

            response.sendRedirect("/profile");
            Service service = new Service(serviceName, description, price);
            service.setUserAccount(userAccount);
            service.setServiceCategory(serviceCategory);
            ServiceQueries.saveService(service);
        }
    }
}
