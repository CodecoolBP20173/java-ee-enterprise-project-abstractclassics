package com.codecool.labourent.dbConnection;

import com.codecool.labourent.config.EntityManagerSingleton;
import com.codecool.labourent.model.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

public class ListPageQueries {

    public static List<Service> getAllRecordsFromTable(){

        EntityManager em = EntityManagerSingleton.getInstance();
        List<Service> serviceList = new ArrayList<>();
        serviceList =  em.createQuery("SELECT allRecords from Service allRecords ").getResultList();
        System.out.println(serviceList);
        return serviceList;
    }

}
