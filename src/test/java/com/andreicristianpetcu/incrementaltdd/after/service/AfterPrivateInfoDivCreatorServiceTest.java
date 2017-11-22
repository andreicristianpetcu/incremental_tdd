package com.andreicristianpetcu.incrementaltdd.after.service;

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
public class AfterPrivateInfoDivCreatorServiceTest {

    private static final long USER_ID = 42L;
    @InjectMocks
    private AfterPrivateInfoDivCreatorService testSubject;
    @Mock
    private AfterPrivateInfoService afterPrivateInfoServiceMock;

    @Test
    public void generatePersonalInfoDiv(){
        when(afterPrivateInfoServiceMock.getSocialSecurityNumber(USER_ID))
                .thenReturn(completedFuture("123456789"));
        when(afterPrivateInfoServiceMock.getFullName(USER_ID))
                .thenReturn(completedFuture("Jane Doe"));

        CompletableFuture<String> divBody = testSubject.generatePersonalInfoDiv(USER_ID);

        assertThat(divBody.join()).isEqualTo("<div>JANE DOE - 123456789</div>");
    }

}