package com.andreicristianpetcu.incrementaltdd.after.service;

import java8.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static java8.util.concurrent.CompletableFuture.completedFuture;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class HelloServiceTest {

    private static final long USER_ID = 42L;
    @InjectMocks
    private HelloService testSubject;

    @Mock
    private EnvironmentService environmentService;
    @Mock
    private EmailProviderService emailProviderServiceMock;

    @Test
    public void sayHello_returnsServerGreeting(){
        when(environmentService.getServerName()).thenReturn(Optional.of("fedora-server"));
        when(emailProviderServiceMock.getEmail(USER_ID)).thenReturn(completedFuture("joe@example.com"));

        String responseMessage = testSubject.sayHello(USER_ID).join();

        assertThat(responseMessage).isEqualTo("Hello joe@example.com and welcome on fedora-server");
    }

}