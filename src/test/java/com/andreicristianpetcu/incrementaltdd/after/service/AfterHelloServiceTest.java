package com.andreicristianpetcu.incrementaltdd.after.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static java8.util.concurrent.CompletableFuture.completedFuture;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AfterHelloServiceTest {

    private static final long USER_ID = 42L;
    @InjectMocks
    private AfterHelloService testSubject;

    @Mock
    private AfterEnvironmentService afterEnvironmentService;
    @Mock
    private AfterEmailProviderService afterEmailProviderServiceMock;

    @Test
    public void sayHello_returnsServerGreeting(){
        when(afterEnvironmentService.getServerName()).thenReturn("fedora-server");
        when(afterEmailProviderServiceMock.getEmail(USER_ID)).thenReturn(completedFuture("joe@example.com"));

        String responseMessage = testSubject.sayHello(USER_ID).join();

        assertThat(responseMessage).isEqualTo("Hello joe@example.com and welcome on fedora-server");
    }

}