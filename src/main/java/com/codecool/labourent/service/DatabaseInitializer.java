/*
package com.codecool.labourent.service;

import com.codecool.labourent.config.EntityManagerSingleton;
import com.codecool.labourent.model.ServiceCategory;
import javax.persistence.EntityManager;


public class DatabaseInitializer {

    private ServiceCategoryService serviceCategoryQueries;

    private DatabaseInitializer(ServiceCategoryService serviceCategoryQueries) {
        this.serviceCategoryQueries = serviceCategoryQueries;
    }

    private void populateDb() {
        ServiceCategory serviceCategory1 = new ServiceCategory("Private Teacher", "A teasdlakdsfdasf");
        ServiceCategory serviceCategory2 = new ServiceCategory("Personal Trainer", "A teasdlaadsfsafdkdsfdasf");
        ServiceCategory serviceCategory3 = new ServiceCategory("Plumber", "A teasdlaasdfsdsdfdsfsafdkdsfdasf");
        ServiceCategory serviceCategory4 = new ServiceCategory("Java Mentor", "A teasdlaadsfsafdkdsfdasf");

        serviceCategoryQueries.saveServiceCategory(serviceCategory1);
        serviceCategoryQueries.saveServiceCategory(serviceCategory2);
        serviceCategoryQueries.saveServiceCategory(serviceCategory3);
        serviceCategoryQueries.saveServiceCategory(serviceCategory4);

        System.out.println("Services saved.");
    }

    public static void main(String[] args) {
        EntityManager entityManager = EntityManagerSingleton.getInstance();
        ServiceCategoryService serviceCategoryQueries = new ServiceCategoryService(entityManager);
        DatabaseInitializer databaseInitializer = new DatabaseInitializer(serviceCategoryQueries);

        databaseInitializer.populateDb();
    }
}
*/
