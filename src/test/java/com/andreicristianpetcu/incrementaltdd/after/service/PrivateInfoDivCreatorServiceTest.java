package com.andreicristianpetcu.incrementaltdd.after.service;

import com.andreicristianpetcu.incrementaltdd.after.model.User;
import java8.util.concurrent.CompletableFuture;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static java8.util.concurrent.CompletableFuture.completedFuture;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PrivateInfoDivCreatorServiceTest {

    private static final long USER_ID = 42L;
    @InjectMocks
    private PrivateInfoDivCreatorService testSubject;
    @Mock
    private PrivateInfoService privateInfoServiceMock;

    @Test
    public void generatePersonalInfoDiv(){
        User user = new User(USER_ID);
        when(privateInfoServiceMock.getSocialSecurityNumber(USER_ID)).thenReturn(completedFuture("123456789"));
        when(privateInfoServiceMock.getFullName(USER_ID)).thenReturn(completedFuture("Jane Doe"));

        String divBody = testSubject.generatePersonalInfoDiv(user).join();

        assertThat(divBody).isEqualTo("<div>JANE DOE - 123456789</div>");
    }

}