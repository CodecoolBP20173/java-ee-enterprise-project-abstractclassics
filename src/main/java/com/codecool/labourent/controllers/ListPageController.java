package com.codecool.labourent.controllers;

import com.codecool.labourent.service.RatingService;
import com.codecool.labourent.service.UserDetailService;
import com.codecool.labourent.service.ServiceCategoryService;
import com.codecool.labourent.service.ServiceService;
import com.codecool.labourent.model.Service;
import com.codecool.labourent.model.ServiceCategory;
import com.codecool.labourent.model.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ListPageController {

    private final ServiceCategoryService serviceCategoryService;

    private final ServiceService serviceService;

    private final UserDetailService userDetailService;

    private final RatingService ratingService;

    @Autowired
    public ListPageController(ServiceCategoryService serviceCategoryService, ServiceService serviceService, UserDetailService userDetailService, RatingService ratingService) {
        this.serviceCategoryService = serviceCategoryService;
        this.serviceService = serviceService;
        this.userDetailService = userDetailService;
        this.ratingService = ratingService;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listServices(Model model, HttpServletRequest request,
                               @RequestParam(value = "column", required=false) String columnName,
                               @RequestParam(value = "sort", required=false) String sortDirection,
                               @RequestParam(value = "categoryId", required=false) String categoryId) {

        List<Integer> ratedServiceList = getRatedServiceIdList(request);
        model.addAttribute("ratedServiceList", ratedServiceList);

        String queryString = request.getQueryString();
        List<ServiceCategory> serviceCategories = serviceCategoryService.getServiceCategories();
        model.addAttribute("serviceCategories", serviceCategories);
        if (queryString == null){
            organizeTable(model);
        } else {
            organizeTable(columnName, sortDirection, categoryId, model);
        }
        return "listPage";
    }

    private void organizeTable(String columnName,
                               String sortDirection,
                               String categoryId,
                               Model model) {
            model.addAttribute("categoryId", categoryId);
            filterSortTable(columnName, sortDirection, categoryId, model);
            switchSortDirection(sortDirection, model);
    }

    private void organizeTable(Model model) {
        List<Service> services = serviceService.getAllRecordsFromTable("id", "asc");
        List<UserDetail> userDetailList = getUserDetailsList(services);
        model.addAttribute("services", services);
        model.addAttribute("sortDirection", "asc");
        model.addAttribute("categoryId", "all");
        model.addAttribute("userDetailsList", userDetailList);
    }

    private void switchSortDirection(String direction, Model model) {

        if (direction.equals("asc")){
           model.addAttribute("sortDirection", "desc");
        } else {
            model.addAttribute("sortDirection", "asc");
        }
    }

    private void filterSortTable(String columnName, String sortDirection, String categoryId, Model model) {
        if (!categoryId.equals("all")) {
            int categoryIdint = Integer.parseInt(categoryId);
            List<Service> services = serviceService.getFilteredRecordsFromTable(columnName, sortDirection, categoryIdint);
            model.addAttribute("services", services);
            model.addAttribute("userDetailsList", getUserDetailsList(services));
        }
        else {
            List<Service> services = serviceService.getAllRecordsFromTable(columnName, sortDirection);
            model.addAttribute("services", serviceService.getAllRecordsFromTable(columnName, sortDirection));
            model.addAttribute("userDetailsList", getUserDetailsList(services));
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

    private List<Integer> getRatedServiceIdList(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        return ratingService.getRatedServiceIdList(userId);
    }
}
