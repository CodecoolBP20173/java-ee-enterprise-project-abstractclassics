package com.codecool.labourent.dbConnection;

import com.codecool.labourent.config.EntityManagerSingleton;
import com.codecool.labourent.model.Gender;
import com.codecool.labourent.model.UserAccount;
import com.codecool.labourent.model.UserDetail;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.Date;

/**
 * This class contains the querries which work on the userdetail relation.
 */
public class ProfilePageQueries {

    /**
     * It gives back a UserDetail instance, if the user has already created a profile. If not, it returns a default instance.
     *
     * @param userId: int
     * @return UserDetail instance
     */
    public static UserDetail getUserDetailById(int userId) throws NoResultException {
        EntityManager entityManager = EntityManagerSingleton.getInstance();
        UserDetail userDetail = entityManager.find(UserDetail.class, userId);

        if (userDetail == null) throw new NoResultException();
        return userDetail;
    }

    /**
     * It puts the new user profile in the userdetail table.
     * @param firstname
     * @param lastname
     * @param phoneNumber
     * @param city
     * @param birthDate
     * @param gender
     * @param intro
     * @param imgUrl
     * @param userAccount
     */
    public static void putUserAccountInDb(String firstname, String lastname, String phoneNumber,
                                          String city, Date birthDate, Gender gender,
                                          String intro, String imgUrl, UserAccount userAccount) {

        UserDetail userDetail = new UserDetail(firstname, lastname, phoneNumber, city, birthDate,
                gender, intro, imgUrl, userAccount);

        EntityManager entityManager = EntityManagerSingleton.getInstance();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(userDetail);
        transaction.commit();
    }

    /**
     * It updates the user profile in the user detail table.
     * @param userId
     * @param firstName
     * @param lastName
     * @param phoneNumber
     * @param city
     * @param birthDate
     * @param gender
     * @param intro
     * @param imgUrl
     */
    public static void updateAccountById(int userId, String firstName, String lastName, String phoneNumber,
                                         String city, Date birthDate, Gender gender,
                                         String intro, String imgUrl) {

        EntityManager entityManager = EntityManagerSingleton.getInstance();

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        UserDetail userDetail = getUserDetailById(userId);
        userDetail.setFirstName(firstName);
        userDetail.setLastName(lastName);
        userDetail.setPhoneNumber(phoneNumber);
        userDetail.setDateOfBirth(birthDate);
        userDetail.setCity(city);
        userDetail.setGender(gender);
        userDetail.setIntroductionText(intro);
        userDetail.setImgUrl(imgUrl);
        transaction.commit();
    }

    /**
     * It checks if the user profile exsists.
     * @param userId
     * @return
     */
    public static boolean isUserAccountExsist(int userId) {
        try {
            getUserDetailById(userId);
        } catch (NoResultException e) {
            System.err.println("No user's details are found by the given user id!");
            return false;
        }
        return true;
    }

}
