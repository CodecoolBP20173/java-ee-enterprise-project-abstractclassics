package com.codecool.labourent.dbConnection;

import com.codecool.labourent.model.ServiceCategory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ServiceCategoryQueriesSaveServiceCategoryTest {
    private EntityManager entityManager;
    private ServiceCategoryQueries serviceCategoryQueries;
    private ServiceCategory serviceCategoryToSave1;
    private ServiceCategory serviceCategoryToSave2;
    private String getServiceCategoriesQuery;

    @BeforeAll
    void setUp() {
        entityManager = Persistence.createEntityManagerFactory("labourentPUTest").createEntityManager();
        serviceCategoryQueries = new ServiceCategoryQueries(entityManager);
        serviceCategoryToSave1 = new ServiceCategory("SC", "This is a service category.");
        serviceCategoryToSave1 = new ServiceCategory("SC2", "This is another service category.");
        getServiceCategoriesQuery = "SELECT s FROM ServiceCategory s";
    }
}