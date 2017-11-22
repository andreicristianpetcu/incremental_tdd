package com.andreicristianpetcu.incrementaltdd.before.service;

import com.andreicristianpetcu.incrementaltdd.before.common.Callback;
import com.andreicristianpetcu.incrementaltdd.before.model.Environment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class HelloServiceTest {

    private static final String USER_ID_PARAM = "42";
    @InjectMocks
    private HelloService testSubject;
    @Mock
    private HttpServletRequest httpServletRequestMock;
    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private HttpServletResponse httpServletResponseMock;
    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private Environment environmentMock;
    @Mock
    private EmailProviderService emailProviderServiceMock;
    @Captor
    private ArgumentCaptor<Callback<String>> stringCallbackCaptor;

    @Test
    public void processAsyncRequest() throws ServletException, IOException {
        when(httpServletRequestMock.getParameter("userId")).thenReturn(USER_ID_PARAM);
        when(environmentMock.getServer().getName()).thenReturn("fedora-server");

        testSubject.processAsyncRequest();

        verify(emailProviderServiceMock).getEmail(eq(42L), stringCallbackCaptor.capture());
        stringCallbackCaptor.getValue().done("joe@example.com");
        ServletOutputStream outputStream = httpServletResponseMock.getOutputStream();
        verify(outputStream).println("Hello joe@example.com and welcome on fedora-server");
    }

}