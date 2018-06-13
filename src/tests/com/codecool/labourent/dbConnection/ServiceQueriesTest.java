package com.codecool.labourent.dbConnection;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;
import javax.persistence.EntityManager;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;



class ServiceQueriesTest {
    private EntityManager entityManagerMock = Mockito.mock(EntityManager.class);
    private ServiceQueries serviceQueries = new ServiceQueries(entityManagerMock);

    private Method getValidateSortDirection() throws NoSuchMethodException {
        Method validateSortDirection = ServiceQueries.class.getDeclaredMethod("validateSortDirection", String.class);
        validateSortDirection.setAccessible(true);
        return validateSortDirection;
    }

    @Test
    void testValidateSortDirectionPassesBackValidatedValue() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method validateSortDirection = getValidateSortDirection();

        assertEquals("ASC", (validateSortDirection.invoke(serviceQueries, "ASC")));
        assertEquals("DESC", validateSortDirection.invoke(serviceQueries, "DESC"));
        assertEquals("asc", (validateSortDirection.invoke(serviceQueries, "asc")));
        assertEquals("desc", (validateSortDirection.invoke(serviceQueries, "desc")));
    }

    @Test
    void testValidateSortDirectionDetectsInvalidArguments() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method validateSortDirection = getValidateSortDirection();

        assertEquals("asc", (validateSortDirection.invoke(serviceQueries, "DROP DATABASE")));
        assertEquals("asc", (validateSortDirection.invoke(serviceQueries, "asdfadsffsad")));
        assertEquals("asc", (validateSortDirection.invoke(serviceQueries, "ASDF$#agdfgdsf")));
        assertEquals("asc", (validateSortDirection.invoke(serviceQueries, "SDFGS")));
    }
}