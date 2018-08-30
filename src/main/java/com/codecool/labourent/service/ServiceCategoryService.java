package com.codecool.labourent.service;

import com.codecool.labourent.model.ServiceCategory;
import com.codecool.labourent.repository.ServiceCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ServiceCategoryService {

    private final ServiceCategoryRepository serviceCategoryRepository;

    @Autowired
    public ServiceCategoryService(ServiceCategoryRepository serviceCategoryRepository) {
        this.serviceCategoryRepository = serviceCategoryRepository;
    }


    public void saveServiceCategory(ServiceCategory serviceCategory) {
        serviceCategoryRepository.save(serviceCategory);
    }

    public List<ServiceCategory> getServiceCategories() {
        return serviceCategoryRepository.findAll();
    }

    public ServiceCategory getServiceCategoryById(int id) {
        return serviceCategoryRepository.findOne(id);
    }
}
