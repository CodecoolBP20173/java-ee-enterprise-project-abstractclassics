package com.codecool.labourent.dbConnection;

import com.codecool.labourent.config.EntityManagerSingleton;
import com.codecool.labourent.model.Service;

import javax.persistence.EntityManager;
import java.util.List;

public class ServiceQueries {

    private static List<String> columnNamesArray;

    public static List<Service> getAllRecordsFromTable(String column, String ascOrDesc){
        EntityManager em = EntityManagerSingleton.getInstance();
        assignColumnNames(em);
        List<Service> serviceList;
        serviceList =  em.createQuery(
                            "SELECT allRecords " +
                            "from Service  allRecords " +
                            "ORDER BY " + validateColumnName(column) + " " +
                            validateSortDirection(ascOrDesc)).getResultList();
        return serviceList;
    }

    private static String validateColumnName (String column){
        if (columnNamesArray.contains(column)) {
            return column;
        } else {
            System.out.println("Illegal injection!");
           return "id";
        }

    }

    private static String validateSortDirection (String sortDirection){
        if (sortDirection.equalsIgnoreCase("asc") | sortDirection.equalsIgnoreCase("desc")) {
            return sortDirection;
        } else {
            System.out.println("Illegal injection!");
            return "asc";
        }

    }

    private static  List<String> assignColumnNames(EntityManager em) {
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
}
