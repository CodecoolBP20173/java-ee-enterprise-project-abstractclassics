package com.codecool.labourent.controllers;

import com.codecool.labourent.dbConnection.ServiceCategoryQueries;
import com.codecool.labourent.dbConnection.ServiceQueries;
import com.codecool.labourent.dbConnection.UserAccountQueries;
import org.junit.jupiter.api.BeforeEach;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class AddServicePageControllerTest {
    private HttpServletRequest requestMock;
    private HttpServletResponse responseMock;
    private ServiceCategoryQueries serviceCategoryQueriesMock;
    private ServiceQueries serviceQueriesMock;
    private UserAccountQueries userAccountQueriesMock;
    private AddServicePageController addServicePageController;

    @BeforeEach
    void setUp() {
        requestMock = mock(HttpServletRequest.class);
        responseMock = mock(HttpServletResponse.class);
        serviceCategoryQueriesMock = mock(ServiceCategoryQueries.class);
        serviceQueriesMock = mock(ServiceQueries.class);
        userAccountQueriesMock = mock(UserAccountQueries.class);
        addServicePageController = new AddServicePageController(serviceCategoryQueriesMock, serviceQueriesMock, userAccountQueriesMock);
    }
}