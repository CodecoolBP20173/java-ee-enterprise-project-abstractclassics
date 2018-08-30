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

    private final UserDetailRepository userDetailRepository;

    @Autowired
    public UserDetailService(UserDetailRepository userDetailRepository) {
        this.userDetailRepository = userDetailRepository;
    }


    /**
     * It gives back a UserDetail instance, if the user has already created a profile. If not, it returns a default instance.
     *
     * @param userId: int
     * @return UserDetail instance
     * @Exeption NoResultException: It throws when user can't be found in the database.
     */
    public UserDetail getUserDetailById(int userId) throws NoResultException {
        UserDetail userDetail = userDetailRepository.findAllById(userId);
        return userDetail;
    }

    /**
     * It updates the user profile in the user detail table.
     * @param userId
     * @param userDetail
     */
    public void updateAccountById(int userId, UserDetail userDetail) {
        userDetail.setId(userId);
        userDetailRepository.save(userDetail);
    }

    /**
     * It puts the new user profile in the userdetail table.
     * @param userDetail
     */
    public void putUserAccountInDb(UserDetail userDetail) {
        userDetailRepository.save(userDetail);
    }

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
