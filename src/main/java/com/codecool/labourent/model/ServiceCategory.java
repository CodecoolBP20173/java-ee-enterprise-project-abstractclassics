package com.codecool.labourent.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ServiceCategory {

    @Id
    private int id;

    private String name;

    private String description;


}
