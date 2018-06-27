package com.codecool.labourent.service;

import com.codecool.labourent.model.Service;
import com.codecool.labourent.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@org.springframework.stereotype.Service
public class ServiceService {

    @Autowired
    private ServiceRepository serviceRepository;

    public List<Service> getAllRecordsFromTable(String column, String ascOrDesc){
        if (column.equals("rating") && ascOrDesc.equalsIgnoreCase("asc") ) {
            return serviceRepository.findAllByOrderByAverageRatingAsc();
        } else if (column.equals("name") && ascOrDesc.equalsIgnoreCase("asc")) {
            return serviceRepository.findAllByOrderByNameAsc();
        } else if (column.equals("price") && ascOrDesc.equalsIgnoreCase("asc")) {
            return serviceRepository.findAllByOrderByPriceAsc();
        } else if (column.equals("rating") && ascOrDesc.equalsIgnoreCase("desc")) {
            return serviceRepository.findAllByOrderByAverageRatingDesc();
        } else if (column.equals("name") && ascOrDesc.equalsIgnoreCase("desc")) {
            return serviceRepository.findAllByOrderByNameDesc();
        } else if (column.equals("price") && ascOrDesc.equalsIgnoreCase("desc")) {
            return serviceRepository.findAllByOrderByPriceDesc();
        }
        return serviceRepository.findAllByOrderByIdAsc();
    }

    public List<Service> getFilteredRecordsFromTable(String column, String ascOrDesc, int servicecategoryId){
        if (column.equals("rating") && ascOrDesc.equalsIgnoreCase("asc") ) {
            return serviceRepository.findByServiceCategoryIdOrderByAverageRatingAsc(servicecategoryId);
        } else if (column.equals("name") && ascOrDesc.equalsIgnoreCase("asc")) {
            return serviceRepository.findByServiceCategoryIdOrderByNameAsc(servicecategoryId);
        } else if (column.equals("price") && ascOrDesc.equalsIgnoreCase("asc")) {
            return serviceRepository.findByServiceCategoryIdOrderByPriceAsc(servicecategoryId);
        } else if (column.equals("rating") && ascOrDesc.equalsIgnoreCase("desc")) {
            return serviceRepository.findByServiceCategoryIdOrderByAverageRatingDesc(servicecategoryId);
        } else if (column.equals("name") && ascOrDesc.equalsIgnoreCase("desc")) {
            return serviceRepository.findByServiceCategoryIdOrderByNameDesc(servicecategoryId);
        } else if (column.equals("price") && ascOrDesc.equalsIgnoreCase("desc")) {
            return serviceRepository.findByServiceCategoryIdOrderByPriceDesc(servicecategoryId);
        }
        return serviceRepository.findByServiceCategoryIdOrderByIdAsc(servicecategoryId);
    }

    public void saveService(Service service) {
        serviceRepository.save(service);
    }

}
