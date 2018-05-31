package com.codecool.labourent.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ServiceCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String description;

    public ServiceCategory(){}

    public ServiceCategory(String name, String description){
        this.name = name;
        this.description = description;
    }


}
