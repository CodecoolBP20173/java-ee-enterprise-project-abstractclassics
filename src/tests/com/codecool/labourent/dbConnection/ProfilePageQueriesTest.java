package com.codecool.labourent.dbConnection;

import com.codecool.labourent.model.UserDetail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ProfilePageQueriesTest {
    private EntityManager entityManagerMock;
    private ProfilePageQueries profilePageQueries;
    private int testUserId;
    private UserDetail userDetailMock;

    @BeforeEach
    void setUp() {
        entityManagerMock = mock(EntityManager.class);
        profilePageQueries = new ProfilePageQueries(entityManagerMock);
        userDetailMock = mock(UserDetail.class);
        testUserId = 1;
    }

    @Test
    void testGetUserById() {
        int otherUserId = 1;
        when(entityManagerMock.find(UserDetail.class, otherUserId)).thenReturn(userDetailMock);
        UserDetail resultUserDetail = profilePageQueries.getUserDetailById(testUserId);
        assertEquals(userDetailMock, resultUserDetail);
    }

    @Test
    void testGetUserByIdException() {
        int otherUserId = 2;
        when(entityManagerMock.find(UserDetail.class, otherUserId)).thenReturn(userDetailMock);
        assertThrows(NoResultException.class, ()->{
            profilePageQueries.getUserDetailById(testUserId); });
    }
}