package com.codecool.labourent.service;

import com.codecool.labourent.model.UserAccount;
import com.codecool.labourent.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

@Service
public class UserAccountService {

    @Autowired
    private UserAccountRepository userAccountRepository;

    public void saveUserAccount(UserAccount userAccount) {
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();
        entityManager.persist(userAccount);
        transaction.commit();
    }

    public boolean userNameIsTaken(String userName) {
        return fieldIsTaken("userName", userName);
    }

    public boolean emailIsTaken(String email) {
        return fieldIsTaken("email", email);
    }

    private boolean fieldIsTaken(String field, String userName) {
        String queryString = "SELECT u, COUNT(u.id) FROM UserAccount u WHERE u." + field + "  = :userName GROUP BY u.id";
        Query userNameQuery = entityManager.createQuery(queryString).setParameter("userName", userName);
        return userNameQuery.getResultList().size() > 0;
    }

    public UserAccount getUserAccountByEmail(String email) {
        String queryString = "SELECT u FROM UserAccount u WHERE u.email  = :email";
        Query userNameQuery = entityManager.createQuery(queryString).setParameter("email", email);

        try {
            return (UserAccount) userNameQuery.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public UserAccount getUserAccountById(int id) {
        return entityManager.find(UserAccount.class, id);
    }
}
