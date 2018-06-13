package com.codecool.labourent.model;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

@Entity
public class UserDetail {
    @Id
    private int id;

    @OneToOne
    @MapsId
    private UserAccount userAccount;

    private String firstName;

    private String lastName;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(columnDefinition = "TEXT")
    private String introductionText;

    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    private String city;

    private String phoneNumber;

    private String imgUrl;

    public UserDetail() {
        this.firstName = "";
        this.lastName = "";
        this.gender = Gender.UNDEFINED;
        this.introductionText = "";
        this.imgUrl = "/static/img/default_profile.png";
        this.dateOfBirth = Calendar.getInstance().getTime();
        this.city = "";
        this.phoneNumber = "";
    }

    public UserDetail(String firstName, String lastName, String phoneNumber,
                      String city, Date dateOfBirth, Gender gender,
                      String introductionText, UserAccount userAccount) {
        this.userAccount = userAccount;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.introductionText = introductionText;
        this.imgUrl = "/static/img/default_profile.png";
        this.dateOfBirth = dateOfBirth;
        this.city = city;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIntroductionText() {
        return introductionText;
    }

    public void setIntroductionText(String introductionText) {
        this.introductionText = introductionText;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getAge() {
        Date currentDate = new Date();
        LocalDate currentDateInLocalDate = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate birthInLocalDate = dateOfBirth.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return Period.between(birthInLocalDate, currentDateInLocalDate).getYears();
    }

    public int getAge(Date currentDate) {
        LocalDate currentDateInLocalDate = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate birthInLocalDate = dateOfBirth.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return Period.between(birthInLocalDate, currentDateInLocalDate).getYears();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

}
