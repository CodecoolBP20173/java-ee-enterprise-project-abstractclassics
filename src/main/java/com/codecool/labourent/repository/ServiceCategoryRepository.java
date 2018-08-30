package com.codecool.labourent.repository;

import com.codecool.labourent.model.ServiceCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceCategoryRepository extends JpaRepository<ServiceCategory, Integer> {
}
