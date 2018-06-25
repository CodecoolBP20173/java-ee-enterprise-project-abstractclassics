package com.codecool.labourent.service;

import com.codecool.labourent.model.Service;
import com.codecool.labourent.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

@org.springframework.stereotype.Service
public class ServiceService {

    @Autowired
    private ServiceRepository serviceRepository;

    private List<String> columnNamesArray;

    public ServiceService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Service> getAllRecordsFromTable(String column, String ascOrDesc){
        assignColumnNames(entityManager);
        List<Service> serviceList;
        serviceList = entityManager.createQuery(
                            "SELECT allRecords " +
                            "from Service allRecords " +
                            "ORDER BY " + validateColumnName(column) + " " +
                            validateSortDirection(ascOrDesc)).getResultList(); //Todo Trasnform this query for being able to sort it by another table
        return serviceList;
    }

    public List<Service> getFilteredRecordsFromTable(String column, String ascOrDesc, int servicecategoryId){
        assignColumnNames(entityManager);
        List<Service> serviceList;
        serviceList = entityManager.createQuery(
                        "SELECT service " +
                        "from Service  service " +
                        "WHERE  service.serviceCategory.id = :servicecategoryId order by " +
                        validateColumnName(column) + " " +
                        validateSortDirection(ascOrDesc)).setParameter("servicecategoryId", servicecategoryId).getResultList();
        return serviceList;
    }

    private String validateColumnName (String column){
        if (columnNamesArray.contains(column.toLowerCase())) {
            return column;
        } else {
           return "id";
        }
    }

    private String validateSortDirection (String sortDirection){
        if (sortDirection.equalsIgnoreCase("asc") | sortDirection.equalsIgnoreCase("desc")) {
            return sortDirection;
        } else {
            return "asc";
        }
    }

    private List<String> assignColumnNames(EntityManager em) {
        if (columnNamesArray == null) {
            columnNamesArray =  em.createNativeQuery (
                                    "select column_name" +
                                    " from information_schema.columns " +
                                    "where table_name='service'"
                                ).getResultList();
            return columnNamesArray;
        } else {
            return columnNamesArray;
        }
    }

    public void saveService(Service service) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(service);
        transaction.commit();
    }
}
