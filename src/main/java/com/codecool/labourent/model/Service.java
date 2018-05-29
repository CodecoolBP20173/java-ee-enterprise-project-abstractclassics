package com.codecool.labourent.model;

import javax.persistence.*;

@Entity
public class Service {
    @Id
    private int id;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    private User user;

    private Double price;

    @ManyToOne
    private ServiceCategory serviceCategory;

    private int numOfRatings;

    private Double sumOfRating;

    @Transient
    private Double averageRating;


    private Double getAverageRating() {
        return sumOfRating / numOfRatings;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getNumOfRatings() {
        return numOfRatings;
    }

    public void setNumOfRatings(int numOfRatings) {
        this.numOfRatings = numOfRatings;
    }

    public Double getSumOfRating() {
        return sumOfRating;
    }

    public void setSumOfRating(Double sumOfRating) {
        this.sumOfRating = sumOfRating;
    }

    public ServiceCategory getServiceCategory() {
        return serviceCategory;
    }

    public void setServiceCategory(ServiceCategory serviceCategory) {
        this.serviceCategory = serviceCategory;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double prize) {
        this.price = prize;
    }
}
