package com.codecool.labourent.dbConnection;

import com.codecool.labourent.config.EntityManagerSingleton;
import com.codecool.labourent.model.UserAccount;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

public class UserAccountQueries {

    public static void saveUserAccount(UserAccount userAccount) {
        EntityManager em = EntityManagerSingleton.getInstance();
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();
        em.persist(userAccount);
        transaction.commit();
    }

    public static boolean userNameIsTaken(String userName) {
        return fieldIsTaken("userName", userName);
    }

    public static boolean emailIsTaken(String email) {
        return fieldIsTaken("email", email);
    }

    private static boolean fieldIsTaken(String field, String userName) {
        EntityManager em = EntityManagerSingleton.getInstance();
        String queryString = "SELECT u, COUNT(u.id) FROM UserAccount u WHERE u." + field + "  = :userName GROUP BY u.id";
        Query userNameQuery = em.createQuery(queryString).setParameter("userName", userName);
        return userNameQuery.getResultList().size() > 0;
    }
}
