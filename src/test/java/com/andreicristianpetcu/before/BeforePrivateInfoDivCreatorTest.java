package com.andreicristianpetcu.before;

import com.andreicristianpetcu.incrementaltdd.before.BeforePrivateInfoDivCreator;
import com.andreicristianpetcu.incrementaltdd.before.BeforePrivateInfoService;
import com.andreicristianpetcu.incrementaltdd.before.Callback;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class BeforePrivateInfoDivCreatorTest {

    private static final long USER_ID = 42L;
    @InjectMocks
    private BeforePrivateInfoDivCreator testSubject;
    @Mock
    private BeforePrivateInfoService beforePrivateInfoServiceMock;
    @Mock
    private Callback<String> callbackMock;
    @Captor
    private ArgumentCaptor<Callback<String>> socialSecurityNumberCallbackCaptor;

    @Test
    public void generatePersonalInfoDiv(){
        testSubject.generatePersonalInfoDiv(USER_ID, "Jane Doe", callbackMock);

        verify(beforePrivateInfoServiceMock).getSocialSecurityNumber(eq(USER_ID), socialSecurityNumberCallbackCaptor.capture());
        Callback<String> socialSecurityNumberCallback = socialSecurityNumberCallbackCaptor.getValue();
        socialSecurityNumberCallback.done("123456789");

        verify(callbackMock).done("<div>JANE DOE - 123456789</div>");
    }

}