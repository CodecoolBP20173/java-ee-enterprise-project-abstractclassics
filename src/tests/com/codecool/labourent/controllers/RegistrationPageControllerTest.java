package com.codecool.labourent.controllers;

import com.codecool.labourent.dbConnection.UserAccountQueries;
import com.codecool.labourent.model.UserAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.mockito.Mockito.*;

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

    @Test
    void testUserIsRedirectedIfEmailIsTaken() throws ServletException, IOException {
        when(userAccountQueriesMock.emailIsTaken(requestMock.getParameter("email"))).thenReturn(true);
        registrationPageController.doPost(requestMock, responseMock);
        verify(responseMock, times(1)).sendRedirect("/registration?taken=email");
    }

    @Test
    void testNewUserIsNotRegisteredIfEmailIsTaken() throws ServletException, IOException {
        when(userAccountQueriesMock.emailIsTaken(requestMock.getParameter("email"))).thenReturn(true);
        ArgumentCaptor<UserAccount> captor = ArgumentCaptor.forClass(UserAccount.class);
        registrationPageController.doPost(requestMock, responseMock);
        verify(userAccountQueriesMock, never()).saveUserAccount(captor.capture());
    }
}