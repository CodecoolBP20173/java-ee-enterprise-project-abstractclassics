package com.codecool.labourent.service;

import com.codecool.labourent.model.UserDetail;
import com.codecool.labourent.repository.UserAccountRepository;
import com.codecool.labourent.repository.UserDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;


/**
 * This class contains the queries which work on the userdetail relation.
 */
@Service
public class UserDetailService {

    @Autowired
    private UserDetailRepository userDetailRepository;


    /**
     * It gives back a UserDetail instance, if the user has already created a profile. If not, it returns a default instance.
     *
     * @param userId: int
     * @return UserDetail instance
     * @Exeption NoResultException: It throws when user can't be found in the database.
     */
    public UserDetail getUserDetailById(int userId) throws NoResultException {
        /*UserDetail userDetail = entityManager.find(UserDetail.class, userId);
        if (userDetail == null) throw new NoResultException();
        return userDetail;*/
        UserDetail userDetail = userDetailRepository.findAllById(userId);
        return userDetail;
    }

    /**
     * It updates the user profile in the user detail table.
     * @param userId
     * @param userDetail
     */
    public void updateAccountById(int userId, UserDetail userDetail) {

        /*userDetailRepository.findAllById(userId).setFirstName(userDetail.getFirstName());
        userDetailRepository.findAllById(userId).setLastName(userDetail.getLastName());
        userDetailRepository.findAllById(userId).setPhoneNumber(userDetail.getPhoneNumber());
        userDetailRepository.findAllById(userId).setDateOfBirth(userDetail.getDateOfBirth());
        userDetailRepository.findAllById(userId).setCity(userDetail.getCity());
        userDetailRepository.findAllById(userId).setGender(userDetail.getGender());
        userDetailRepository.findAllById(userId).setIntroductionText(userDetail.getIntroductionText());
        userDetailRepository.findAllById(userId).setImgUrl(userDetail.getImgUrl());*/

        /*UserDetail userDetailFromDB = userDetailRepository.findAllById(userId);
        userDetailFromDB.setId(userId);
        userDetailFromDB.setFirstName(userDetail.getFirstName());
        userDetailFromDB.setLastName(userDetail.getLastName());
        userDetailFromDB.setPhoneNumber(userDetail.getPhoneNumber());
        userDetailFromDB.setDateOfBirth(userDetail.getDateOfBirth());
        userDetailFromDB.setCity(userDetail.getCity());
        userDetailFromDB.setGender(userDetail.getGender());
        userDetailFromDB.setIntroductionText(userDetail.getIntroductionText());
        userDetailFromDB.setImgUrl(userDetail.getImgUrl());*/
        userDetail.setId(userId);
        userDetailRepository.save(userDetail);



        /*EntityTransaction transaction = entityManager.getTransaction();
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
        transaction.commit();*/
    }

    /**
     * It puts the new user profile in the userdetail table.
     * @param userDetail
     */
    public void putUserAccountInDb(UserDetail userDetail) {

        userDetailRepository.save(userDetail);

        /*EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(userDetail);
        transaction.commit();*/
    }


    /*public boolean isUserAccountExist(int userId) {
        try {
            getUserDetailById(userId);
        } catch (NoResultException e) {
            System.err.println("No user's details are found by the given user id!");
            return false;
        }
        return true;
    }*/

    public void saveUserDetail(UserDetail userDetail) {
        userDetailRepository.save(userDetail);
    }

    public boolean isUserAccountExist(int userId) {
        try {
            getUserDetailById(userId);
        } catch (NoResultException e) {
            System.err.println("No user's details are found by the given user id!");
            return false;
        }
        return true;
    }
}
