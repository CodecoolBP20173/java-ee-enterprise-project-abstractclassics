package com.codecool.labourent.config;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class EntityManagerSingleton {

    private static final EntityManager em  = Persistence.createEntityManagerFactory("labourentPU").createEntityManager();


    public static EntityManager getInstance(){
        return em;
    }

    private EntityManagerSingleton() {

    }
}
