/*
package com.codecool.labourent.config;

import com.codecool.labourent.controllers.*;
import com.codecool.labourent.service.UserDetailService;
import com.codecool.labourent.service.ServiceCategoryService;
import com.codecool.labourent.service.ServiceService;
import com.codecool.labourent.service.UserAccountService;

import javax.persistence.EntityManager;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


@WebListener
public class Initializer implements ServletContextListener {

    private EntityManager entityManager;

    private AddServicePageController addServicePageController;
    private IndexPageController indexPageController;
    private IntroductionPageController introductionPageController;
    private ListPageController listPageController;
    private LoginPageController loginPageController;
    private LogoutPageController logoutPageController;
    private ProfilePageController profilePageController;
    private RegistrationPageController registrationPageController;
    private ServicePageController servicePageController;

    private UserDetailService profilePageQueries;
    private ServiceCategoryService serviceCategoryQueries;
    private ServiceService serviceQueries;
    private UserAccountService userAccountQueries;


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();

        createEntityManager();
        instantiateQueries();
        instantiateControllers();

        context.addServlet("addServicePageController", addServicePageController).addMapping("/add-service");
        context.addServlet("indexPageController", indexPageController).addMapping("/");
        context.addServlet("introductionPageController", introductionPageController).addMapping("/introduction");
        context.addServlet("listPageController", listPageController).addMapping("/list");
        context.addServlet("loginPageController", loginPageController).addMapping("/login");
        context.addServlet("logoutPageController", logoutPageController).addMapping("/logout");
        context.addServlet("profilePageController", profilePageController).addMapping("/profile");
        context.addServlet("registrationPageController", registrationPageController).addMapping("/registration");
        context.addServlet("servicePageController", servicePageController).addMapping("/service");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }


    private void instantiateControllers(){
        addServicePageController = new AddServicePageController(serviceCategoryQueries, serviceQueries, userAccountQueries);
        indexPageController = new IndexPageController();
        introductionPageController = new IntroductionPageController();
        listPageController = new ListPageController(serviceCategoryQueries, serviceQueries, profilePageQueries);
        loginPageController = new LoginPageController(userAccountQueries);
        logoutPageController = new LogoutPageController();
        profilePageController = new ProfilePageController(profilePageQueries, userAccountQueries);
        registrationPageController = new RegistrationPageController(userAccountQueries);
        servicePageController = new ServicePageController();

    }

    private void instantiateQueries() {
        profilePageQueries = new UserDetailService(entityManager);
        serviceCategoryQueries = new ServiceCategoryService(entityManager);
        serviceQueries = new ServiceService(entityManager);
        userAccountQueries = new UserAccountService(entityManager);
    }

    private void createEntityManager() {
        this.entityManager = EntityManagerSingleton.getInstance();
    }
}
*/
