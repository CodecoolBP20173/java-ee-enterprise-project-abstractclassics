package com.codecool.labourent.service;

import com.codecool.labourent.model.UserAccount;
import com.codecool.labourent.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserAccountService implements UserDetailsService{

    private final UserAccountRepository userAccountRepository;

    @Autowired
    public UserAccountService(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    public void saveUserAccount(UserAccount userAccount) {
        userAccountRepository.save(userAccount);
    }

    public boolean userNameIsTaken(String userName) {
        return userAccountRepository.findByUserName(userName) != null;
    }

    public boolean emailIsTaken(String email) {
        return userAccountRepository.findByEmail(email) != null;
    }

    public UserAccount getUserAccountByEmail(String email) {
        return userAccountRepository.findByEmail(email);
    }
    public UserAccount getUserAccountById(int id) {
        return userAccountRepository.findOne(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount userAccount = getUserAccountByEmail(username);

        if (userAccount == null) {
            throw new UsernameNotFoundException(username);
        }

        return new AuthenticationService(userAccount);
    }
}
