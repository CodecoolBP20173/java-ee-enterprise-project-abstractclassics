package com.codecool.labourent.controllers;

import com.codecool.labourent.config.TemplateEngineUtil;
import com.codecool.labourent.service.UserDetailService;
import com.codecool.labourent.service.ServiceCategoryService;
import com.codecool.labourent.service.ServiceService;
import com.codecool.labourent.model.Service;
import com.codecool.labourent.model.ServiceCategory;
import com.codecool.labourent.model.UserDetail;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ListPageController extends HttpServlet {

    private ServiceCategoryService serviceCategoryService;
    private ServiceService serviceService;
    private UserDetailService userDetailService;

    public ListPageController(ServiceCategoryService serviceCategoryService, ServiceService serviceService, UserDetailService userDetailService) {
        this.serviceCategoryService = serviceCategoryService;
        this.serviceService = serviceService;
        this.userDetailService = userDetailService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        List<ServiceCategory> serviceCategories = serviceCategoryService.getServiceCategories();
        context.setVariable("serviceCategories", serviceCategories);

        origanizeTable(req, context);
        engine.process("listPage.html", context, resp.getWriter());
    }

    private void origanizeTable(HttpServletRequest req, WebContext context) {
        String queryString = req.getQueryString();
        if (queryString != null){
            String columnName = req.getParameter("column");
            String sortDirection = req.getParameter("sort");
            String categoryId = req.getParameter("categoryId");
            context.setVariable("categoryId", categoryId);
            filterSortTable(req, context, columnName, sortDirection);
            switchSortDirection(sortDirection, context);

        } else {
            List<Service> services = serviceService.getAllRecordsFromTable("id", "asc");
            List<UserDetail> userDetailList = getUserDetailsList(services);
            context.setVariable("services", services);
            context.setVariable("sortDirection", "asc");
            context.setVariable("categoryId", "all");
            context.setVariable("userDetailsList", userDetailList);
        }
    }

    private void switchSortDirection(String direction, WebContext context) {

        if (direction.equals("asc")){
            context.setVariable("sortDirection", "desc");
        } else {
            context.setVariable("sortDirection", "asc");
        }
    }

    private void filterSortTable(HttpServletRequest req, WebContext context, String columnName, String sortDirection) {
        if (!req.getParameter("categoryId").equals("all")) {
            int categoryId = Integer.parseInt(req.getParameter("categoryId"));
            List<Service> services = serviceService.getFilteredRecordsFromTable(columnName, sortDirection, categoryId); //Todo
            context.setVariable("services", services);
            context.setVariable("userDetailsList", getUserDetailsList(services));
        }
        else {
            List<Service> services = serviceService.getAllRecordsFromTable(columnName, sortDirection);
            context.setVariable("services", serviceService.getAllRecordsFromTable(columnName, sortDirection));  //Todo
            context.setVariable("userDetailsList", getUserDetailsList(services));
        }
    }

    private List<UserDetail> getUserDetailsList(List<Service> services){
        List<UserDetail> userDetailsList = new ArrayList<>();
        for (Service service: services) {
            int userAccountId = service.getUserAccount().getId();
            UserDetail userDetail = userDetailService.getUserDetailById(userAccountId);
            userDetailsList.add(userDetail);
        }
        return userDetailsList;

    }


}
