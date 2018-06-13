package com.codecool.labourent.controllers;

import com.codecool.labourent.dbConnection.UserAccountQueries;
import org.junit.jupiter.api.BeforeEach;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.mock;

class RegistrationPageControllerTest {

    private HttpServletRequest requestMock;
    private HttpServletResponse responseMock;
    private UserAccountQueries userAccountQueriesMock;
    private RegistrationPageController registrationPageController;

    @BeforeEach
    void setUp() {
        requestMock = mock(HttpServletRequest.class);
        responseMock = mock(HttpServletResponse.class);
        userAccountQueriesMock = mock(UserAccountQueries.class);
        registrationPageController = new RegistrationPageController(userAccountQueriesMock);
    }
}