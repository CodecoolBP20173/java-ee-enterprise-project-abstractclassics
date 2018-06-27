package com.codecool.labourent.repository;

import com.codecool.labourent.model.Rating;
import com.codecool.labourent.model.Service;
import com.codecool.labourent.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Integer> {


    @Query(value = "select * from rating where rating.useraccount_id=:useraccountId and rating.service_id=:serviceId", nativeQuery = true)
    List<Rating> findByUseraccount(@Param("useraccountId") Integer useraccountId, @Param("serviceId") Integer serviceId);


    @Query(value = "INSERT into rating(service_id, useraccount_id)\n" +
            "    values (:serviceId, :useraccountId) RETURNING rating.id ", nativeQuery = true)
    void saveRating(@Param("useraccountId") Integer useraccountId, @Param("serviceId") Integer serviceId);
}
