/*
package com.codecool.labourent.service;

import com.codecool.labourent.model.ServiceCategory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ServiceCategoryServiceTest {
    private EntityManager entityManager;
    private ServiceCategoryService serviceCategoryService;
    private ServiceCategory serviceCategory;

    @BeforeAll
    void setUp() {
        entityManager = Persistence.createEntityManagerFactory("labourentPUTest").createEntityManager();
        serviceCategory = new ServiceCategory("SC", "This is a service category.");
        serviceCategoryService = new ServiceCategoryService(entityManager);
        serviceCategoryService.saveServiceCategory(serviceCategory);
    }

    @Test
    void testGetServiceCategories() {
        List<ServiceCategory> serviceCategories = serviceCategoryService.getServiceCategories();
        assertEquals(1, serviceCategories.size());
        assertEquals(serviceCategory, serviceCategories.get(0));
    }

    @Test
    void testGetServiceCategoryByIdIfIdDoesNotExist() {
        int id = 2;
        assertNull(serviceCategoryService.getServiceCategoryById(id));
    }

    @Test
    void testGetServiceCategoryByIdIfIdExists() {
        int id = 1;
        assertEquals(serviceCategory, serviceCategoryService.getServiceCategoryById(id));
    }
}*/
