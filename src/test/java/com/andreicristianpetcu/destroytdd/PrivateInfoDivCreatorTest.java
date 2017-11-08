package com.andreicristianpetcu.destroytdd;

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
public class PrivateInfoDivCreatorTest {

    private static final long USER_ID = 42L;
    @InjectMocks
    private PrivateInfoDivCreator testSubject;
    @Mock
    private PrivateInfoService privateInfoServiceMock;

    @Test
    public void generatePersonalInfoDiv(){
        when(privateInfoServiceMock.getSocialSecurityNumber(USER_ID)).thenReturn(completedFuture("123456789"));

        CompletableFuture<String> divBody = testSubject.generatePersonalInfoDiv(USER_ID, "Jane Doe");

        assertThat(divBody.join()).isEqualTo("<div>JANE DOE - 123456789</div>");
    }

}