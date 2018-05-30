package com.codecool.labourent.dbConnection;

import com.codecool.labourent.config.EntityManagerSingleton;
import com.codecool.labourent.model.UserAccount;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class UserAccountQueries {

    public static void saveUserAccount(UserAccount userAccount) {
        EntityManager em = EntityManagerSingleton.getInstance();
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();
        em.persist(userAccount);
        transaction.commit();
    }
}
