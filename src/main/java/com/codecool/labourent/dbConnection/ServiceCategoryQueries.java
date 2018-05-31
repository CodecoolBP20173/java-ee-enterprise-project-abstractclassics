package com.codecool.labourent.dbConnection;

import com.codecool.labourent.config.EntityManagerSingleton;
import com.codecool.labourent.model.ServiceCategory;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class ServiceCategoryQueries {

    private static EntityManager entityManager = EntityManagerSingleton.getInstance();

    public static void saveServiceCategory(ServiceCategory serviceCategory) {
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();
        entityManager.persist(serviceCategory);
        transaction.commit();
    }

    public static List<ServiceCategory> getServiceCategories() {
        String queryString = "SELECT s FROM ServiceCategory s";
        Query serviceCategoryQuery = entityManager.createQuery(queryString);
        return serviceCategoryQuery.getResultList();
    }

    public static ServiceCategory getServiceCategoryById(int id) {
        return entityManager.find(ServiceCategory.class, id);
    }
}
