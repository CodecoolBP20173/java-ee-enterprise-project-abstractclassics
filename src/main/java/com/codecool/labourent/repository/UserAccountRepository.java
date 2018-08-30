package com.codecool.labourent.repository;

import com.codecool.labourent.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount, Integer> {

    UserAccount findByUserName(String userName);

    UserAccount findByEmail(String email);
}
