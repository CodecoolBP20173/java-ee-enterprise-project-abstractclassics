package com.codecool.labourent.model;

import com.codecool.labourent.controllers.ProfilePageController;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.spy;

class UserDetailTest {
    @Test
    void getAge() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate = Calendar.getInstance().getTime();
        Date currentDate = Calendar.getInstance().getTime();
        UserDetail userDetailMock = new UserDetail();

        int expectedAge = 20; //"2018-05-29"

        try {
            birthDate = sdf.parse("1997-07-21");
            currentDate = sdf.parse("2018-05-29");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        userDetailMock.setDateOfBirth(birthDate);
        assertEquals(expectedAge, userDetailMock.getAge(currentDate));
    }

    @Test
    void testEquals() {

        Date dateOfBirth = ProfilePageController.getFormatDate("2021-01-01");
        UserDetail firstUserDetail = new UserDetail("Apple", "Peach", "1456624",
                "Budapest", dateOfBirth, Gender.valueOf("FEMALE"),
                "hello", null);
        firstUserDetail.setImgUrl("/");
        UserDetail secondUserDetail = new UserDetail("Apple", "Peach", "1456624",
                "Budapest", dateOfBirth, Gender.valueOf("FEMALE"),
                "hello", null);
        secondUserDetail.setImgUrl("/");

        assertEquals(firstUserDetail,secondUserDetail);
    }

    @Test
    void testNotEquals() {

        Date dateOfBirth = ProfilePageController.getFormatDate("2021-01-01");
        UserDetail firstUserDetail = new UserDetail("Apple", "Peach", "1456624",
                "Budapest", dateOfBirth, Gender.valueOf("FEMALE"),
                "hello", null);
        firstUserDetail.setImgUrl("/");
        UserDetail secondUserDetail = new UserDetail("Apple", "Peach", "1456624",
                "Budapest", dateOfBirth, Gender.valueOf("FEMALE"),
                "hi", null);
        secondUserDetail.setImgUrl("/");

        assertNotEquals(firstUserDetail,secondUserDetail);
    }
}
