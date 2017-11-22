package com.andreicristianpetcu.incrementaltdd.before.service;

import com.andreicristianpetcu.incrementaltdd.before.common.Callback;
import com.andreicristianpetcu.incrementaltdd.before.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PrivateInfoDivCreatorServiceTest {

    private static final long USER_ID = 42L;
    @InjectMocks
    private PrivateInfoDivCreatorService testSubject;
    @Mock
    private PrivateInfoService privateInfoServiceMock;
    @Mock
    private Callback<String> callbackMock;
    @Mock
    private User userMock;
    @Captor
    private ArgumentCaptor<Callback<String>> socialSecurityNumberCallbackCaptor;
    @Captor
    private ArgumentCaptor<Callback<String>> fullNameCallbackCaptor;

    @Test
    public void generatePersonalInfoDiv(){
        when(userMock.getId()).thenReturn(USER_ID);

        testSubject.generatePersonalInfoDiv(userMock, callbackMock);

        verify(privateInfoServiceMock).getSocialSecurityNumber(eq(USER_ID), socialSecurityNumberCallbackCaptor.capture());
        Callback<String> socialSecurityNumberCallback = socialSecurityNumberCallbackCaptor.getValue();
        socialSecurityNumberCallback.done("123456789");
        verify(privateInfoServiceMock).getFullName(eq(USER_ID), fullNameCallbackCaptor.capture());
        Callback<String> fullNameCallback = fullNameCallbackCaptor.getValue();
        fullNameCallback.done("Jane Doe");

        verify(callbackMock).done("<div>JANE DOE - 123456789</div>");
    }

}