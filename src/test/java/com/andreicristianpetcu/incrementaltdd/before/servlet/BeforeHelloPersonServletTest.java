package com.andreicristianpetcu.incrementaltdd.before.servlet;

import com.andreicristianpetcu.incrementaltdd.before.service.BeforeHelloService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.ServletException;
import java.io.IOException;

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