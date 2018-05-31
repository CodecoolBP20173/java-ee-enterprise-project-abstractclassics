package com.codecool.labourent.dbConnection;

import com.codecool.labourent.config.EntityManagerSingleton;
import com.codecool.labourent.model.Gender;
import com.codecool.labourent.model.UserAccount;
import com.codecool.labourent.model.UserDetail;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.Date;

/**
 * This class contains the querries which work on the userdetail relation.
 */
public class ProfilePageQueries {

    /**
     * It gives back a UserDetail instance, if the user has already created a profile. If not, it returns a default instance.
     * @param userId: int
     * @return UserDetail instance
     */
    public static UserDetail getUserDetailById(int userId) {
        EntityManager entityManager = EntityManagerSingleton.getInstance();
        UserDetail userDetails = new UserDetail();

        try {
            TypedQuery<UserDetail> queryUserDetail =
                    entityManager.createQuery("SELECT c FROM UserDetail c WHERE c.id = :userId", UserDetail.class);
            userDetails = queryUserDetail.setParameter("userId", userId).getSingleResult();

            System.err.println(queryUserDetail);
        } catch (NoResultException e) {
            System.err.println("No user's details are found by the given user id!");
        }
        return userDetails;
    }


    public static void putUserAccountInDb(String firstname, String lastname, String phoneNumber,
                                          String city, Date birthDate, Gender gender,
                                          String intro, String imgUrl, UserAccount userAccount) {

        UserDetail userDetail = new UserDetail();
        userDetail.setFirstName(firstname);
        userDetail.setLastName(lastname);
        userDetail.setDateOfBirth(birthDate);
        userDetail.setIntroductionText(intro);
        userDetail.setGender(gender);
        userDetail.setCity(city);
        userDetail.setPhoneNumber(phoneNumber);
        userDetail.setImgUrl(imgUrl);
        userDetail.setUserAccount(userAccount);

        EntityManager entityManager = EntityManagerSingleton.getInstance();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(userDetail);
        transaction.commit();
        System.out.println(userDetail.getLastName() + " saved.");
    }

}
