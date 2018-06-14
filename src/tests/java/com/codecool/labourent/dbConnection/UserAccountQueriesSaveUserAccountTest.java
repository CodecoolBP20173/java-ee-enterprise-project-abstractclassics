package com.codecool.labourent.dbConnection;

import com.codecool.labourent.model.UserAccount;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserAccountQueriesSaveUserAccountTest {
    private EntityManager entityManager;
    private UserAccountQueries userAccountQueries;
    private UserAccount userAccountToSave;
    private String getUserAccountsQuery;

    @BeforeAll
    void setUp() {
        entityManager = Persistence.createEntityManagerFactory("labourentPUTest").createEntityManager();
        userAccountToSave = new UserAccount("asd", "asd@asd.hu", "pw");
        userAccountQueries = new UserAccountQueries(entityManager);
        getUserAccountsQuery = "SELECT u FROM UserAccount u";
        userAccountQueries.saveUserAccount(userAccountToSave);
    }

    @Test
    void testUserIsSaved() {
        List<UserAccount> userAccounts = entityManager.createQuery(getUserAccountsQuery).getResultList();
        assertEquals(1, userAccounts.size());
    }

    @Test
    void testUserIsSavedWithCorrectData() {
        UserAccount userAccountFromDB = entityManager.find(UserAccount.class, 1);
        assertEquals("asd", userAccountFromDB.getUserName());
        assertEquals("asd@asd.hu", userAccountFromDB.getEmail());
        assertEquals("pw", userAccountFromDB.getPassword());
    }
}