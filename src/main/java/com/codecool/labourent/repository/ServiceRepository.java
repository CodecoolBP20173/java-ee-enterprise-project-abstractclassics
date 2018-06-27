package com.codecool.labourent.repository;

import com.codecool.labourent.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ServiceRepository extends JpaRepository<Service, Integer> {
    List<Service> findAllByOrderByIdAsc();
    List<Service> findAllByOrderByIdDesc();

    List<Service> findAllByOrderByNameAsc();
    List<Service> findAllByOrderByNameDesc();

    List<Service> findAllByOrderByPriceAsc();
    List<Service> findAllByOrderByPriceDesc();

    List<Service> findByServiceCategoryIdOrderByIdAsc(Integer serviceCategoryId);
    List<Service> findByServiceCategoryIdOrderByIdDesc(Integer serviceCategoryId);
    List<Service> findByServiceCategoryIdOrderByNameAsc(Integer serviceCategoryId);
    List<Service> findByServiceCategoryIdOrderByNameDesc(Integer serviceCategoryId);
    List<Service> findByServiceCategoryIdOrderByPriceAsc(Integer serviceCategoryId);
    List<Service> findByServiceCategoryIdOrderByPriceDesc(Integer serviceCategoryId);

    @Query(value = "select column_name from information_schema.columns where table_name='service'", nativeQuery = true)
    public List<String> getColumnNames();
}