package com.codecool.labourent.dbConnection;

import com.codecool.labourent.config.EntityManagerSingleton;
import com.codecool.labourent.model.UserDetail;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

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


}
