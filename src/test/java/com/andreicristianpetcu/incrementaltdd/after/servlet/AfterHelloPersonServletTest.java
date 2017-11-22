package com.andreicristianpetcu.incrementaltdd.after.servlet;

import com.andreicristianpetcu.incrementaltdd.after.service.AfterEnvironmentService;
import com.andreicristianpetcu.incrementaltdd.after.service.AfterHelloService;
import java8.util.concurrent.CompletableFuture;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AfterHelloPersonServletTest {

    private static final String USER_ID_PARAM = "42";
    public static final String SERVICE_RESPONSE = "Hello response for user with id 42";
    @InjectMocks
    private AfterHelloPersonServlet testSubject;

    @Mock
    private AfterHelloService afterHelloService;

    @Mock
    private HttpServletRequest httpServletRequestMock;
    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private HttpServletResponse httpServletResponseMock;

    @Test
    public void service_delegatesToService() throws ServletException, IOException {
        when(httpServletRequestMock.getParameter(AfterHelloPersonServlet.USER_ID_PARAM_NAME)).thenReturn(USER_ID_PARAM);
        when(afterHelloService.sayHello(42)).thenReturn(CompletableFuture.completedFuture(SERVICE_RESPONSE));

        testSubject.service(httpServletRequestMock, httpServletResponseMock);

        verify(httpServletResponseMock.getOutputStream()).println(SERVICE_RESPONSE);
    }

}

