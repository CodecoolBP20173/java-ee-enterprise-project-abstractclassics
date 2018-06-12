package com.codecool.labourent.dbConnection;

import com.codecool.labourent.config.EntityManagerSingleton;
import com.codecool.labourent.model.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class ServiceQueries {

    private EntityManager entityManager;
    private List<String> columnNamesArray;

    public ServiceQueries(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Service> getAllRecordsFromTable(String column, String ascOrDesc){
        assignColumnNames(entityManager);
        List<Service> serviceList;
        serviceList = entityManager.createQuery(
                            "SELECT allRecords " +
                            "from Service  allRecords " +
                            "ORDER BY " + validateColumnName(column) + " " +
                            validateSortDirection(ascOrDesc)).getResultList();
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
            System.out.println("Illegal injection!");
            System.out.println("Fuck you");
           return "id";
        }

    }

    private String validateSortDirection (String sortDirection){
        if (sortDirection.equalsIgnoreCase("asc") | sortDirection.equalsIgnoreCase("desc")) {
            return sortDirection;
        } else {
            System.out.println("Illegal injection!");
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
