package com.codecool.labourent.controllers;

import com.codecool.labourent.config.TemplateEngineUtil;
import com.codecool.labourent.dbConnection.ProfilePageQueries;
import com.codecool.labourent.dbConnection.ServiceCategoryQueries;
import com.codecool.labourent.dbConnection.ServiceQueries;
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

    private ServiceCategoryQueries serviceCategoryQueries;
    private ServiceQueries serviceQueries;
    private ProfilePageQueries profilePageQueries;

    public ListPageController(ServiceCategoryQueries serviceCategoryQueries, ServiceQueries serviceQueries, ProfilePageQueries profilePageQueries) {
        this.serviceCategoryQueries = serviceCategoryQueries;
        this.serviceQueries = serviceQueries;
        this.profilePageQueries = profilePageQueries;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        List<ServiceCategory> serviceCategories = serviceCategoryQueries.getServiceCategories();
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
            List<Service> services = serviceQueries.getAllRecordsFromTable("id", "asc");
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
            List<Service> services = serviceQueries.getFilteredRecordsFromTable(columnName, sortDirection, categoryId); //Todo
            context.setVariable("services", services);
            context.setVariable("userDetailsList", getUserDetailsList(services));
        }
        else {
            List<Service> services = serviceQueries.getAllRecordsFromTable(columnName, sortDirection);
            context.setVariable("services", serviceQueries.getAllRecordsFromTable(columnName, sortDirection));  //Todo
            context.setVariable("userDetailsList", getUserDetailsList(services));
        }
    }

    private List<UserDetail> getUserDetailsList(List<Service> services){
        List<UserDetail> userDetailsList = new ArrayList<>();
        for (Service service: services) {
            int userAccountId = service.getUserAccount().getId();
            UserDetail userDetail = profilePageQueries.getUserDetailById(userAccountId);
            userDetailsList.add(userDetail);
        }
        return userDetailsList;

    }


}
