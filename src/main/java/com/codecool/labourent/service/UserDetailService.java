package com.codecool.labourent.service;

import com.codecool.labourent.model.UserDetail;
import com.codecool.labourent.repository.UserDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

        UserDetail userDetail = userDetailRepository.findUserDetailByUserAccountId(userId);
        if (userDetail == null) throw new NoResultException();
        return userDetail;
    }

/**
     * It updates the user profile in the user detail table.
     * @param userId
     * @param userDetail
     */

    public void updateAccountById(int userId, UserDetail userDetail) {

        UserDetail userDetailFromDB = getUserDetailById(userId);
        userDetailFromDB.setFirstName(userDetail.getFirstName());
        userDetailFromDB.setLastName(userDetail.getLastName());
        userDetailFromDB.setPhoneNumber(userDetail.getPhoneNumber());
        userDetailFromDB.setDateOfBirth(userDetail.getDateOfBirth());
        userDetailFromDB.setCity(userDetail.getCity());
        userDetailFromDB.setGender(userDetail.getGender());
        userDetailFromDB.setIntroductionText(userDetail.getIntroductionText());
        userDetailFromDB.setImgUrl(userDetail.getImgUrl());
        userDetailRepository.save(userDetailFromDB);


    }

/*
*
     * It puts the new user profile in the userdetail table.
     * @param userDetail
*/


    public void putUserAccountInDb(UserDetail userDetail) {
        userDetailRepository.save(userDetail);
    }

/**
     * It checks if the user profile exsists.
     * @param userId
     * @return boolean
     */

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
