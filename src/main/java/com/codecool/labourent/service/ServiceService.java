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
        if (column.equals("id") && ascOrDesc.equalsIgnoreCase("asc") ) {
            return serviceRepository.findAllByOrderByIdAsc();
        } else if (column.equals("name") && ascOrDesc.equalsIgnoreCase("asc")) {
            return serviceRepository.findAllByOrderByNameAsc();
        } else if (column.equals("price") && ascOrDesc.equalsIgnoreCase("asc")) {
            return serviceRepository.findAllByOrderByPriceAsc();
        } else if (column.equals("id") && ascOrDesc.equalsIgnoreCase("desc")) {
            return serviceRepository.findAllByOrderByIdDesc();
        } else if (column.equals("name") && ascOrDesc.equalsIgnoreCase("desc")) {
            return serviceRepository.findAllByOrderByNameDesc();
        } else if (column.equals("price") && ascOrDesc.equalsIgnoreCase("desc")) {
            return serviceRepository.findAllByOrderByPriceDesc();
        }
        return serviceRepository.findAllByOrderByIdAsc();
    }

    public List<Service> getFilteredRecordsFromTable(String column, String ascOrDesc, int servicecategoryId){
        if (column.equals("id") && ascOrDesc.equalsIgnoreCase("asc") ) {
            return serviceRepository.findByServiceCategoryIdOrderByIdAsc(servicecategoryId);
        } else if (column.equals("name") && ascOrDesc.equalsIgnoreCase("asc")) {
            return serviceRepository.findByServiceCategoryIdOrderByNameAsc(servicecategoryId);
        } else if (column.equals("price") && ascOrDesc.equalsIgnoreCase("asc")) {
            return serviceRepository.findByServiceCategoryIdOrderByPriceAsc(servicecategoryId);
        } else if (column.equals("id") && ascOrDesc.equalsIgnoreCase("desc")) {
            return serviceRepository.findByServiceCategoryIdOrderByIdDesc(servicecategoryId);
        } else if (column.equals("name") && ascOrDesc.equalsIgnoreCase("desc")) {
            return serviceRepository.findByServiceCategoryIdOrderByNameDesc(servicecategoryId);
        } else if (column.equals("price") && ascOrDesc.equalsIgnoreCase("desc")) {
            return serviceRepository.findByServiceCategoryIdOrderByPriceDesc(servicecategoryId);
        }
        return serviceRepository.findAllByOrderByIdAsc();
    }
}
