package com.codecool.labourent.repository;

import com.codecool.labourent.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service, Integer> {

}