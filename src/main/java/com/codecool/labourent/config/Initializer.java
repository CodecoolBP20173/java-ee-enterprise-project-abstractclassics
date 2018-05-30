package com.codecool.labourent.config;

import com.codecool.labourent.model.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Initializer {
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

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("labourentPU");
        EntityManager em = emf.createEntityManager();

        populateDb(em);

        em.close();
        emf.close();

    }
}
