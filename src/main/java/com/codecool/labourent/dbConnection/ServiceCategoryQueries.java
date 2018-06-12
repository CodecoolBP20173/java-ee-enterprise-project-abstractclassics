package com.codecool.labourent.dbConnection;

import com.codecool.labourent.model.ServiceCategory;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class ServiceCategoryQueries {

    private EntityManager entityManager;

    public ServiceCategoryQueries(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void saveServiceCategory(ServiceCategory serviceCategory) {
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();
        entityManager.persist(serviceCategory);
        transaction.commit();
    }

    public List<ServiceCategory> getServiceCategories() {
        String queryString = "SELECT s FROM ServiceCategory s";
        Query serviceCategoryQuery = entityManager.createQuery(queryString);
        return serviceCategoryQuery.getResultList();
    }

    public ServiceCategory getServiceCategoryById(int id) {
        return entityManager.find(ServiceCategory.class, id);
    }
}
