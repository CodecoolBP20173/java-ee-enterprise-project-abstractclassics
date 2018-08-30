package com.codecool.labourent.repository.serviceRepository;

import com.codecool.labourent.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ServiceRepository extends JpaRepository<Service, Integer> {
    List<Service> findAllByOrderByIdAsc();
    List<Service> findAllByOrderByNameAsc();
    List<Service> findAllByOrderByNameDesc();
    List<Service> findAllByOrderByPriceAsc();
    List<Service> findAllByOrderByPriceDesc();
    Service findById(Integer serviceId);



}