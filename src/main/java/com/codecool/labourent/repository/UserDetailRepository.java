package com.codecool.labourent.repository;

import com.codecool.labourent.model.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailRepository extends JpaRepository<UserDetail, Integer> {
    UserDetail findUserDetailByUserAccountId(Integer id);
}