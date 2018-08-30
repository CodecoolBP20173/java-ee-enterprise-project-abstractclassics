package com.codecool.labourent.service;

import com.codecool.labourent.model.Service;
import com.codecool.labourent.repository.serviceRepository.FilterServiceRepository;
import com.codecool.labourent.repository.serviceRepository.RatingServiceRepository;
import com.codecool.labourent.repository.serviceRepository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@org.springframework.stereotype.Service
public class ServiceService {

    private final ServiceRepository serviceRepository;
    private final RatingServiceRepository ratingServiceRepository;
    private final FilterServiceRepository filterServiceRepository;

    @Autowired
    public ServiceService(ServiceRepository serviceRepository, RatingServiceRepository ratingServiceRepository, FilterServiceRepository filterServiceRepository) {
        this.serviceRepository = serviceRepository;
        this.ratingServiceRepository = ratingServiceRepository;
        this.filterServiceRepository = filterServiceRepository;
    }

    public List<Service> getAllRecordsFromTable(String column, String ascOrDesc){
        if (column.equals("rating") && ascOrDesc.equalsIgnoreCase("asc") ) {
            return ratingServiceRepository.findAllByOrderByAverageRatingAsc();
        } else if (column.equals("name") && ascOrDesc.equalsIgnoreCase("asc")) {
            return serviceRepository.findAllByOrderByNameAsc();
        } else if (column.equals("price") && ascOrDesc.equalsIgnoreCase("asc")) {
            return serviceRepository.findAllByOrderByPriceAsc();
        } else if (column.equals("rating") && ascOrDesc.equalsIgnoreCase("desc")) {
            return ratingServiceRepository.findAllByOrderByAverageRatingDesc();
        } else if (column.equals("name") && ascOrDesc.equalsIgnoreCase("desc")) {
            return serviceRepository.findAllByOrderByNameDesc();
        } else if (column.equals("price") && ascOrDesc.equalsIgnoreCase("desc")) {
            return serviceRepository.findAllByOrderByPriceDesc();
        }
        return serviceRepository.findAllByOrderByIdAsc();
    }

    public List<Service> getFilteredRecordsFromTable(String column, String ascOrDesc, int servicecategoryId){
        if (column.equals("rating") && ascOrDesc.equalsIgnoreCase("asc") ) {
            return ratingServiceRepository.findByServiceCategoryIdOrderByAverageRatingAsc(servicecategoryId);
        } else if (column.equals("name") && ascOrDesc.equalsIgnoreCase("asc")) {
            return filterServiceRepository.findByServiceCategoryIdOrderByNameAsc(servicecategoryId);
        } else if (column.equals("price") && ascOrDesc.equalsIgnoreCase("asc")) {
            return filterServiceRepository.findByServiceCategoryIdOrderByPriceAsc(servicecategoryId);
        } else if (column.equals("rating") && ascOrDesc.equalsIgnoreCase("desc")) {
            return ratingServiceRepository.findByServiceCategoryIdOrderByAverageRatingDesc(servicecategoryId);
        } else if (column.equals("name") && ascOrDesc.equalsIgnoreCase("desc")) {
            return filterServiceRepository.findByServiceCategoryIdOrderByNameDesc(servicecategoryId);
        } else if (column.equals("price") && ascOrDesc.equalsIgnoreCase("desc")) {
            return filterServiceRepository.findByServiceCategoryIdOrderByPriceDesc(servicecategoryId);
        }
        return filterServiceRepository.findByServiceCategoryIdOrderByIdAsc(servicecategoryId);
    }

    public void saveService(Service service) {
        serviceRepository.save(service);
    }

    public void increaseRatingStats(Integer rating, Integer serviceId) {
        Service service = serviceRepository.findById(serviceId);
        service.setSumOfRating(service.getSumOfRating() + rating);
        service.setNumOfRatings(service.getNumOfRatings() + 1);
        saveService(service);
    }

}
