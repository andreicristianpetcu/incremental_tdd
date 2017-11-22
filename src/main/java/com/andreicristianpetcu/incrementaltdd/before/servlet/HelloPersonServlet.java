package com.andreicristianpetcu.incrementaltdd.before.servlet;

import com.andreicristianpetcu.incrementaltdd.before.service.HelloService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HelloPersonServlet extends HttpServlet {

    private HelloService helloService;

    public HelloPersonServlet(HelloService helloService) {
        this.helloService = helloService;
    }

    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        helloService.processAsyncRequest();
    }
}

