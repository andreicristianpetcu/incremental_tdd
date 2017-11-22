package com.andreicristianpetcu.incrementaltdd.before.service;

import com.andreicristianpetcu.incrementaltdd.before.common.Callback;
import com.andreicristianpetcu.incrementaltdd.before.model.Environment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HelloService {

    private HttpServletRequest httpServletRequest;
    private HttpServletResponse httpServletResponse;
    private Environment environment;
    private EmailProviderService emailProviderService;

    public HelloService(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Environment environment, EmailProviderService emailProviderService) {
        this.httpServletRequest = httpServletRequest;
        this.httpServletResponse = httpServletResponse;
        this.environment = environment;
        this.emailProviderService = emailProviderService;
    }

    public void processAsyncRequest(){
        Long userId = Long.valueOf(httpServletRequest.getParameter("userId"));
        final String serverName = environment.getServer().getName();

        emailProviderService.getEmail(userId, new Callback<String>() {
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