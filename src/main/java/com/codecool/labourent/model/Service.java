package com.codecool.labourent.model;

import javax.persistence.*;

@Entity
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    private UserAccount userAccount;

    private Double price;

    @ManyToOne
    private ServiceCategory serviceCategory;

    private int numOfRatings;

    private Double sumOfRating;

    @Transient
    private Double averageRating;

    public Service() {
    }

    public Service(String name, String description, Double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

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

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
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

    public Service(String name, Double price, Double averageRating) {
        this.name = name;
        this.price = price;
        this.averageRating = averageRating;
    }
}
