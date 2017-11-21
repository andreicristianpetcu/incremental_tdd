package com.andreicristianpetcu.before;

import com.andreicristianpetcu.incrementaltdd.before.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class BeforeHelloPersonServletTest {

    @InjectMocks
    private BeforeHelloPersonServlet testSubject;
    @Mock
    private BeforeHelloService beforeHelloService;

    @Test
    public void generatePersonalInfoDiv() throws ServletException, IOException {
        testSubject.service(null, null);

        verify(beforeHelloService).processAsyncRequest();
    }

}