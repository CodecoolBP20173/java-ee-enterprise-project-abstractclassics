package com.codecool.labourent.controllers;

import com.codecool.labourent.dbConnection.ServiceCategoryQueries;
import com.codecool.labourent.dbConnection.ServiceQueries;
import com.codecool.labourent.dbConnection.UserAccountQueries;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AddServicePageControllerTest {
    private HttpServletRequest requestMock;
    private HttpServletResponse responseMock;
    private ServiceCategoryQueries serviceCategoryQueriesMock;
    private ServiceQueries serviceQueriesMock;
    private UserAccountQueries userAccountQueriesMock;
    private HttpSession httpSessionMock;
    private AddServicePageController addServicePageController;

    @BeforeEach
    void setUp() {
        requestMock = mock(HttpServletRequest.class);
        responseMock = mock(HttpServletResponse.class);
        serviceCategoryQueriesMock = mock(ServiceCategoryQueries.class);
        serviceQueriesMock = mock(ServiceQueries.class);
        userAccountQueriesMock = mock(UserAccountQueries.class);
        httpSessionMock = mock(HttpSession.class);
        addServicePageController = new AddServicePageController(serviceCategoryQueriesMock, serviceQueriesMock, userAccountQueriesMock);
    }

    @BeforeEach
    void setAttributes() {
        when(requestMock.getParameter("name")).thenReturn("Service");
        when(requestMock.getParameter("description")).thenReturn("Description");
        when(requestMock.getParameter("price")).thenReturn("1");
        when(requestMock.getParameter("serviceCategoryId")).thenReturn("1");
        when(requestMock.getSession()).thenReturn(httpSessionMock);
        when(httpSessionMock.getAttribute("userId")).thenReturn(1);
    }

    @Test
    void testUserAccountIsQueried() throws ServletException, IOException {
        addServicePageController.doPost(requestMock, responseMock);
        ArgumentCaptor<Integer> captor = ArgumentCaptor.forClass(Integer.class);
        verify(userAccountQueriesMock, times(1)).getUserAccountById(captor.capture());
    }

    @Test
    void testServiceCategoryIsQueried() throws ServletException, IOException {
        addServicePageController.doPost(requestMock, responseMock);
        ArgumentCaptor<Integer> captor = ArgumentCaptor.forClass(Integer.class);
        verify(serviceCategoryQueriesMock, times(1)).getServiceCategoryById(captor.capture());
    }

    @Test
    void testUserIsRedirectedToProfilePage() throws ServletException, IOException {
        addServicePageController.doPost(requestMock, responseMock);
        verify(responseMock, times(1)).sendRedirect("/profile");
    }

    @Test
    void testServiceIsSaved() throws ServletException, IOException {
        addServicePageController.doPost(requestMock, responseMock);
    }
}