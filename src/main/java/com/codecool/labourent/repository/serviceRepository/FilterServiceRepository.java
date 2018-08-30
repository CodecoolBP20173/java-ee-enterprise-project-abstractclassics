package com.codecool.labourent.repository.serviceRepository;

import com.codecool.labourent.model.Service;

import java.util.List;

public interface FilterServiceRepository extends ServiceRepository {
    List<Service> findByServiceCategoryIdOrderByNameAsc(Integer serviceCategoryId);
    List<Service> findByServiceCategoryIdOrderByNameDesc(Integer serviceCategoryId);
    List<Service> findByServiceCategoryIdOrderByPriceAsc(Integer serviceCategoryId);
    List<Service> findByServiceCategoryIdOrderByPriceDesc(Integer serviceCategoryId);
    List<Service> findByServiceCategoryIdOrderByIdAsc(Integer serviceCategoryId);
}
