package com.codecool.labourent.dbConnection;

import com.codecool.labourent.model.ServiceCategory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ServiceCategoryQueriesTest {
    private EntityManager entityManager;
    private ServiceCategoryQueries serviceCategoryQueries;
    private ServiceCategory serviceCategory;

    @BeforeAll
    void setUp() {
        entityManager = Persistence.createEntityManagerFactory("labourentPUTest").createEntityManager();
        serviceCategory = new ServiceCategory("SC", "This is a service category.");
        serviceCategoryQueries = new ServiceCategoryQueries(entityManager);
        serviceCategoryQueries.saveServiceCategory(serviceCategory);
    }

    @Test
    void testGetServiceCategories() {
        List<ServiceCategory> serviceCategories = serviceCategoryQueries.getServiceCategories();
        assertEquals(1, serviceCategories.size());
        assertEquals(serviceCategory, serviceCategories.get(0));
    }

    @Test
    void testGetServiceCategoryByIdIfIdDoesNotExist() {
        int id = 2;
        assertNull(serviceCategoryQueries.getServiceCategoryById(id));
    }

    @Test
    void testGetServiceCategoryByIdIfIdExists() {
        int id = 1;
        assertEquals(serviceCategory, serviceCategoryQueries.getServiceCategoryById(id));
    }
}