package com.codecool.labourent.dbConnection;

import com.codecool.labourent.config.EntityManagerSingleton;
import com.codecool.labourent.model.ServiceCategory;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class ServiceCategoryQueries {

    public static void saveService(ServiceCategory serviceCategory) {
        EntityManager em = EntityManagerSingleton.getInstance();
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();
        em.persist(serviceCategory);
        transaction.commit();
    }

    public static List<ServiceCategory> getServiceCategories() {
        EntityManager em = EntityManagerSingleton.getInstance();
        String queryString = "SELECT s FROM ServiceCategory s";
        Query serviceCategoryQuery = em.createQuery(queryString);
        return serviceCategoryQuery.getResultList();
    }
}
