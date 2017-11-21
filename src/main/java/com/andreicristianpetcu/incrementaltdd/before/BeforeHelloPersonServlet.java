package com.andreicristianpetcu.incrementaltdd.before;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BeforeHelloPersonServlet extends HttpServlet {

    private BeforeHelloService beforeHelloService;

    public BeforeHelloPersonServlet(BeforeHelloService beforeHelloService) {
        this.beforeHelloService = beforeHelloService;
    }

    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        beforeHelloService.processAsyncRequest();
    }
}

