package com.codecool.labourent.controllers;

import com.codecool.labourent.dbConnection.ProfilePageQueries;
import com.codecool.labourent.dbConnection.UserAccountQueries;
import com.codecool.labourent.model.Gender;
import com.codecool.labourent.model.UserDetail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

class ProfilePageControllerTest {

    HttpServletRequest httpServletRequestMock;
    ProfilePageQueries profilePageQueriesMock;
    UserAccountQueries userAccountQueriesMock;
    UserDetail expectedUserDetail;
    ProfilePageController controller;
    int userId;

    @BeforeEach
    void settingRequest() {
        userId = 0;

        Date dateOfBirth = ProfilePageController.getFormatDate("2021-01-01");

        expectedUserDetail = new UserDetail("Apple", "Peach", "1456624",
                "Budapest", dateOfBirth, Gender.valueOf("FEMALE"),
                "hello", null);
        expectedUserDetail.setImgUrl("/");

        httpServletRequestMock = mock(HttpServletRequest.class);
        when(httpServletRequestMock.getParameter("firstname")).thenReturn("Apple");
        when(httpServletRequestMock.getParameter("lastname")).thenReturn("Peach");
        when(httpServletRequestMock.getParameter("phonenumber")).thenReturn("1456624");
        when(httpServletRequestMock.getParameter("city")).thenReturn("Budapest");
        when(httpServletRequestMock.getParameter("radioGender")).thenReturn("FEMALE");
        when(httpServletRequestMock.getParameter("imageInput")).thenReturn("/");
        when(httpServletRequestMock.getParameter("introTextarea")).thenReturn("hello");
        when(httpServletRequestMock.getParameter("birthday")).thenReturn("2021-01-01");

        profilePageQueriesMock = mock(ProfilePageQueries.class);
        userAccountQueriesMock = mock(UserAccountQueries.class);
        controller = spy(new ProfilePageController(profilePageQueriesMock, userAccountQueriesMock));

    }

    @Test
    void testGetFormatDate() {
        Date testDate = new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime();
        Date resultDate = ProfilePageController.getFormatDate("2014-02-11");

        assertEquals(testDate, resultDate);
    }

    @Test
    void testCreateUserDetail() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method createUserDetail = ProfilePageController.class.getDeclaredMethod("createUserDetail", HttpServletRequest.class, int.class);
        createUserDetail.setAccessible(true);

        UserDetail resultDetail = (UserDetail) createUserDetail.invoke(controller, httpServletRequestMock, userId);
        assertEquals(expectedUserDetail, resultDetail);

    }

    @Test
    void testRequestUserDetails() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method requestUserDetails = ProfilePageController.class.getDeclaredMethod("requestUserDetails", int.class, UserDetail.class);
        requestUserDetails.setAccessible(true);

        when(profilePageQueriesMock.getUserDetailById(userId)).thenReturn(expectedUserDetail);

        UserDetail testDetail = mock(UserDetail.class);
        UserDetail resultDetail = (UserDetail) requestUserDetails.invoke(controller, userId, testDetail);

        assertEquals(expectedUserDetail, resultDetail);
    }
}