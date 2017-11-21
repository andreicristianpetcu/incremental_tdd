package com.andreicristianpetcu.incrementaltdd.before;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BeforeHelloService {

    private HttpServletRequest httpServletRequest;
    private HttpServletResponse httpServletResponse;
    private Environment environment;
    private BeforeEmailProviderService beforeEmailProviderService;

    public BeforeHelloService(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Environment environment, BeforeEmailProviderService beforeEmailProviderService) {
        this.httpServletRequest = httpServletRequest;
        this.httpServletResponse = httpServletResponse;
        this.environment = environment;
        this.beforeEmailProviderService = beforeEmailProviderService;
    }

    public void processAsyncRequest(){
        Long userId = Long.valueOf(httpServletRequest.getParameter("userId"));
        final String serverName = environment.getServer().getName();

        beforeEmailProviderService.getEmail(userId, new Callback<String>() {
            public void done(String email) {
                try {
                    httpServletResponse.getOutputStream().println("Hello " + email + " and welcome on " + serverName);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

}