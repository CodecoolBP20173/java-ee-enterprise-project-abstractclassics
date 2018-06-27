/*
package com.codecool.labourent.service;

import com.codecool.labourent.model.UserAccount;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserAccountServiceTest {

    private EntityManager entityManager;
    private UserAccountService userAccountService;
    private UserAccount userAccount;

    @BeforeAll
    void setUp() {
        entityManager = Persistence.createEntityManagerFactory("labourentPUTest").createEntityManager();
        userAccount = new UserAccount("asd", "asd@asd.hu", "pw");
        userAccountService = new UserAccountService(entityManager);
        userAccountService.saveUserAccount(userAccount);
    }

    @Test
    void testEmailIsTaken() {
        String email = "asd@asd.hu";
        assertTrue(userAccountService.emailIsTaken(email));
    }

    @Test
    void testEmailIsNotTaken() {
        String email = "qwe@qwe.hu";
        assertFalse(userAccountService.emailIsTaken(email));
    }

    @Test
    void testUserNameIsTaken() {
        String username = "asd";
        assertTrue(userAccountService.userNameIsTaken(username));
    }

    @Test
    void testUserNameIsNotTaken() {
        String username = "qwe";
        assertFalse(userAccountService.userNameIsTaken(username));
    }

    @Test
    void testGetUserAccountByEmailIfEmailDoesNotExist() {
        String email = "qwe@qwe.hu";
        assertNull(userAccountService.getUserAccountByEmail(email));
    }

    @Test
    void testGetUserAccountByEmailIfEmailExists() {
        String email = "asd@asd.hu";
        assertEquals(userAccount, userAccountService.getUserAccountByEmail(email));
    }

    @Test
    void testGetUserAccountByIdIfIdDoesNotExist() {
        int id = 2;
        assertNull(userAccountService.getUserAccountById(id));
    }

    @Test
    void testGetUserAccountByIdIfIdExists() {
        int id = 1;
        assertEquals(userAccount, userAccountService.getUserAccountById(id));
    }
}*/
