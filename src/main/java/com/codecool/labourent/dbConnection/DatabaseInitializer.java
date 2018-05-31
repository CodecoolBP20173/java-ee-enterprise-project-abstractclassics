package com.codecool.labourent.dbConnection;

import com.codecool.labourent.config.EntityManagerSingleton;
import com.codecool.labourent.model.Service;
import com.codecool.labourent.model.ServiceCategory;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class DatabaseInitializer {
    public static void populateDb(EntityManager em) {


        /*Service service1 = new Service("Cleaning", 25.0, 4.5);
        Service service2 = new Service("Math teacher", 23.0, 4.9);
        Service service3 = new Service("Java teacher", 27.0, 3.8);
        Service service4 = new Service("Plumber", 20.0, 4.4);
        Service service5 = new Service("Personal Trainer", 19.0, 5.0);*/

        ServiceCategory serviceCategory1 = new ServiceCategory("Private Teacher", "A teasdlakdsfdasf");
        ServiceCategory serviceCategory2 = new ServiceCategory("Personal Trainer", "A teasdlaadsfsafdkdsfdasf");
        ServiceCategory serviceCategory3 = new ServiceCategory("Plumber", "A teasdlaasdfsdsdfdsfsafdkdsfdasf");
        ServiceCategory serviceCategory4 = new ServiceCategory("Java Mentor", "A teasdlaadsfsafdkdsfdasf");

        ServiceCategoryQueries.saveServiceCategory(serviceCategory1);
        ServiceCategoryQueries.saveServiceCategory(serviceCategory2);
        ServiceCategoryQueries.saveServiceCategory(serviceCategory3);
        ServiceCategoryQueries.saveServiceCategory(serviceCategory4);

        /*service1.setServiceCategory(serviceCategory1);
        service2.setServiceCategory(serviceCategory1);
        service3.setServiceCategory(serviceCategory1);
        service4.setServiceCategory(serviceCategory2);
        service5.setServiceCategory(serviceCategory2);*/


        /*EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        *//*em.persist(serviceCategory1);
        em.persist(serviceCategory2);*//*

        *//*em.persist(service1);
        em.persist(service2);
        em.persist(service3);
        em.persist(service4);
        em.persist(service5);*//*

        *//*em.persist(serviceCategory1);
        em.persist(serviceCategory2);*//*
        transaction.commit();
*/
        System.out.println("Services saved.");
    }

    public static void main(String[] args) {


        EntityManager em = EntityManagerSingleton.getInstance();
        populateDb(em);



    }
}
