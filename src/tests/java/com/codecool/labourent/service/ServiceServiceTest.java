package com.codecool.labourent.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;
import javax.persistence.EntityManager;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;



class ServiceServiceTest {
    private EntityManager entityManagerMock = Mockito.mock(EntityManager.class);
    private ServiceService serviceService = new ServiceService(entityManagerMock);

    private Method getValidateSortDirection() throws NoSuchMethodException {
        Method validateSortDirection = ServiceService.class.getDeclaredMethod("validateSortDirection", String.class);
        validateSortDirection.setAccessible(true);
        return validateSortDirection;
    }

    @Test
    void testValidateSortDirectionPassesBackValidatedValue() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method validateSortDirection = getValidateSortDirection();

        assertEquals("ASC", (validateSortDirection.invoke(serviceService, "ASC")));
        assertEquals("DESC", validateSortDirection.invoke(serviceService, "DESC"));
        assertEquals("asc", (validateSortDirection.invoke(serviceService, "asc")));
        assertEquals("desc", (validateSortDirection.invoke(serviceService, "desc")));
    }
    @Test
    void testValidateSortDirectionDetectsInvalidArguments() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method validateSortDirection = getValidateSortDirection();

        assertEquals("asc", (validateSortDirection.invoke(serviceService, "DROP DATABASE")));
        assertEquals("asc", (validateSortDirection.invoke(serviceService, "asdfadsffsad")));
        assertEquals("asc", (validateSortDirection.invoke(serviceService, "ASDF$#agdfgdsf")));
        assertEquals("asc", (validateSortDirection.invoke(serviceService, "SDFGS")));
    }
}