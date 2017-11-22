package com.andreicristianpetcu.incrementaltdd.after.servlet;

import com.andreicristianpetcu.incrementaltdd.after.service.AfterHelloService;
import java8.util.concurrent.CompletableFuture;
import java8.util.function.Consumer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AfterHelloPersonServlet {

    static final String USER_ID_PARAM_NAME = "userId";
    private AfterHelloService afterHelloService;

    public AfterHelloPersonServlet(AfterHelloService afterHelloService) {
        this.afterHelloService = afterHelloService;
    }

    public void service(HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse) {
        Long userId = Long.valueOf(httpServletRequest.getParameter(USER_ID_PARAM_NAME));

        CompletableFuture<String> helloServiceResponse = afterHelloService.sayHello(userId);

        helloServiceResponse.thenAccept(new Consumer<String>() {
            public void accept(String messageFromServer) {
                try {
                    httpServletResponse.getOutputStream().println(messageFromServer);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}