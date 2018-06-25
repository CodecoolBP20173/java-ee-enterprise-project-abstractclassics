package com.codecool.labourent.controllers;

import com.codecool.labourent.service.UserAccountService;
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
    private UserAccountService userAccountServiceMock;
    private RegistrationPageController registrationPageController;

    @BeforeEach
    void setUp() {
        requestMock = mock(HttpServletRequest.class);
        responseMock = mock(HttpServletResponse.class);
        userAccountServiceMock = mock(UserAccountService.class);
        registrationPageController = new RegistrationPageController(userAccountServiceMock);
    }

    @Test
    void testUserIsRedirectedIfEmailIsTaken() throws ServletException, IOException {
        when(userAccountServiceMock.emailIsTaken(requestMock.getParameter("email"))).thenReturn(true);
        registrationPageController.doPost(requestMock, responseMock);
        verify(responseMock, times(1)).sendRedirect("/registration?taken=email");
    }

    @Test
    void testNewUserIsNotRegisteredIfEmailIsTaken() throws ServletException, IOException {
        when(userAccountServiceMock.emailIsTaken(requestMock.getParameter("email"))).thenReturn(true);
        ArgumentCaptor<UserAccount> captor = ArgumentCaptor.forClass(UserAccount.class);
        registrationPageController.doPost(requestMock, responseMock);
        verify(userAccountServiceMock, never()).saveUserAccount(captor.capture());
    }

    @Test
    void testUserIsRedirectedIfUsernameIsTaken() throws ServletException, IOException {
        when(userAccountServiceMock.emailIsTaken(requestMock.getParameter("email"))).thenReturn(false);
        when(userAccountServiceMock.userNameIsTaken(requestMock.getParameter("username"))).thenReturn(true);
        registrationPageController.doPost(requestMock, responseMock);
        verify(responseMock, times(1)).sendRedirect("/registration?taken=username");
    }

    @Test
    void testNewUserIsNotRegisteredIfUsernameIsTaken() throws ServletException, IOException {
        when(userAccountServiceMock.emailIsTaken(requestMock.getParameter("email"))).thenReturn(false);
        when(userAccountServiceMock.userNameIsTaken(requestMock.getParameter("username"))).thenReturn(true);
        ArgumentCaptor<UserAccount> captor = ArgumentCaptor.forClass(UserAccount.class);
        registrationPageController.doPost(requestMock, responseMock);
        verify(userAccountServiceMock, never()).saveUserAccount(captor.capture());
    }

    @Test
    void testRegisterNewUserIfDataIsCorrect() throws ServletException, IOException {
        when(userAccountServiceMock.emailIsTaken(requestMock.getParameter("email"))).thenReturn(false);
        when(userAccountServiceMock.userNameIsTaken(requestMock.getParameter("username"))).thenReturn(false);
        ArgumentCaptor<UserAccount> captor = ArgumentCaptor.forClass(UserAccount.class);
        registrationPageController.doPost(requestMock, responseMock);
        verify(userAccountServiceMock, times(1)).saveUserAccount(captor.capture());
    }
}