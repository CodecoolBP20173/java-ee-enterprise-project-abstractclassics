package com.codecool.labourent.dbConnection;

import com.codecool.labourent.controllers.ProfilePageController;
import com.codecool.labourent.model.Gender;
import com.codecool.labourent.model.UserAccount;
import com.codecool.labourent.model.UserDetail;
import org.junit.jupiter.api.*;

import static org.mockito.Mockito.mock;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProfilePageQueriesTest {
    private EntityManager entityManager;
    private ProfilePageQueries profilePageQueries;
    private UserDetail userDetail1;
    private UserDetail userDetail2;

    @BeforeAll
    void setUp() {
        entityManager = Persistence.createEntityManagerFactory("labourentPUTest").createEntityManager();
        profilePageQueries = new ProfilePageQueries(entityManager);

        Date dateOfBirth = ProfilePageController.getFormatDate("2021-01-01");
        UserAccount userAccount = new UserAccount("peach", "asd@asd.hu", "pw");
        userDetail1 = new UserDetail("Apple", "Peach", "1456624", "Budapest",
                dateOfBirth, Gender.valueOf("FEMALE"), "hello", userAccount);
        userDetail1.setImgUrl("/");
        putInTheDB(userDetail1);
    }

    @Test
    void testPutUserAccountInDB() {
        int userId = 2;
        Date dateOfBirth = ProfilePageController.getFormatDate("2011-01-01");
        UserAccount userAccount2 = new UserAccount("lime", "limed@asd.hu", "pw");
        userDetail2 = new UserDetail("Grape", "Lime", "456789", "Cegléd",
                dateOfBirth, Gender.valueOf("MALE"), "hi", userAccount2);
        userDetail2.setImgUrl("/");

        profilePageQueries.putUserAccountInDb(userDetail2);

        UserDetail result = entityManager.find(UserDetail.class, userId);
        assertEquals(userDetail2, result);
    }

    @Test
    void testGetUserById() {
        int otherUserId = 1;
        UserDetail resultUserDetail = profilePageQueries.getUserDetailById(otherUserId);
        assertEquals(userDetail1, resultUserDetail);
    }

    @Test
    void testGetUserByIdException() {
        int otherUserId = 3;
        assertThrows(NoResultException.class, ()->{
            profilePageQueries.getUserDetailById(otherUserId); });
    }

    @Test
    void testUpdateAccountById() {
        int testUserId = 1;
        String expected = "Visegrád";

        userDetail1.setCity(expected);
        profilePageQueries.updateAccountById(testUserId, userDetail1);
        String result = profilePageQueries.getUserDetailById(testUserId).getCity();

        assertEquals(expected, result);
    }

    @Test
    void testIsExistUserAccount() {
        int testUserId = 1;
        assertTrue(profilePageQueries.isUserAccountExist(testUserId));
    }

    @Test
    void testIsNotExistUserAccount() {
        int testUserId = 3;
        assertFalse(profilePageQueries.isUserAccountExist(testUserId));
    }

    @AfterAll
    void tearDown() {
        entityManager.clear();
        entityManager.close();
    }

    private void putInTheDB(UserDetail userDetail) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(userDetail);
        transaction.commit();
    }
}