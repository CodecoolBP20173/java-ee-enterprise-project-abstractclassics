package com.codecool.labourent.dbConnection;

import com.codecool.labourent.model.UserAccount;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserAccountQueriesTest {

    private EntityManager entityManager;
    private UserAccountQueries userAccountQueries;
    private UserAccount userAccount;

    @BeforeAll
    void setUp() {
        entityManager = Persistence.createEntityManagerFactory("labourentPUTest").createEntityManager();
        userAccount = new UserAccount("asd", "asd@asd.hu", "pw");
        userAccountQueries = new UserAccountQueries(entityManager);
        userAccountQueries.saveUserAccount(userAccount);
    }

    @Test
    void testEmailIsTaken() {
        String email = "asd@asd.hu";
        assertTrue(userAccountQueries.emailIsTaken(email));
    }

    @Test
    void testEmailIsNotTaken() {
        String email = "qwe@qwe.hu";
        assertFalse(userAccountQueries.emailIsTaken(email));
    }

    @Test
    void testUserNameIsTaken() {
        String username = "asd";
        assertTrue(userAccountQueries.userNameIsTaken(username));
    }

    @Test
    void testUserNameIsNotTaken() {
        String username = "qwe";
        assertFalse(userAccountQueries.userNameIsTaken(username));
    }

    @Test
    void testGetUserAccountByEmailIfEmailDoesNotExist() {
        String email = "qwe@qwe.hu";
        assertNull(userAccountQueries.getUserAccountByEmail(email));
    }

    @Test
    void testGetUserAccountByEmailIfEmailExists() {
        String email = "asd@asd.hu";
        assertEquals(userAccount, userAccountQueries.getUserAccountByEmail(email));
    }
}