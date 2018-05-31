package com.codecool.labourent.dbConnection;

import com.codecool.labourent.config.EntityManagerSingleton;
import com.codecool.labourent.model.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class DatabaseInitializer {
    public static void populateDb(EntityManager em) {


        Service service1 = new Service("Cleaning", 25.0, 4.5);
        Service service2 = new Service("Math teacher", 23.0, 4.9);
        Service service3 = new Service("Java teacher", 27.0, 3.8);
        Service service4 = new Service("Plumber", 20.0, 4.4);
        Service service5 = new Service("Personal Trainer", 19.0, 5.0);

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(service1);
        em.persist(service2);
        em.persist(service3);
        em.persist(service4);
        em.persist(service5);
        transaction.commit();

        System.out.println("Services saved.");
    }

    public static void main(String[] args) {


        EntityManager em = EntityManagerSingleton.getInstance();
        populateDb(em);



    }
}
