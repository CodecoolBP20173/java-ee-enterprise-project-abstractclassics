package com.codecool.labourent.dbConnection;

import com.codecool.labourent.model.UserDetail;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;


/**
 * This class contains the queries which work on the userdetail relation.
 */
public class ProfilePageQueries {

    private EntityManager entityManager;

    public ProfilePageQueries(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * It gives back a UserDetail instance, if the user has already created a profile. If not, it returns a default instance.
     *
     * @param userId: int
     * @return UserDetail instance
     */
    public UserDetail getUserDetailById(int userId) throws NoResultException {
        UserDetail userDetail = entityManager.find(UserDetail.class, userId);

        if (userDetail == null) throw new NoResultException();
        return userDetail;
    }

    /**
     * It updates the user profile in the user detail table.
     * @param userId
     * @param userDetail
     */
    public void updateAccountById(int userId, UserDetail userDetail) {

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        UserDetail userDetailFromDB = getUserDetailById(userId);
        userDetailFromDB.setFirstName(userDetail.getFirstName());
        userDetailFromDB.setLastName(userDetail.getLastName());
        userDetailFromDB.setPhoneNumber(userDetail.getPhoneNumber());
        userDetailFromDB.setDateOfBirth(userDetail.getDateOfBirth());
        userDetailFromDB.setCity(userDetail.getCity());
        userDetailFromDB.setGender(userDetail.getGender());
        userDetailFromDB.setIntroductionText(userDetail.getIntroductionText());
        userDetailFromDB.setImgUrl(userDetail.getImgUrl());
        transaction.commit();
    }
}
