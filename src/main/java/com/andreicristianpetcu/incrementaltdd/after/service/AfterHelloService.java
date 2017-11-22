package com.andreicristianpetcu.incrementaltdd.after.service;

import java8.util.concurrent.CompletableFuture;
import java8.util.function.Function;

public class AfterHelloService {

    private final AfterEmailProviderService afterEmailProviderService;
    private final AfterEnvironmentService afterEnvironmentService;

    public AfterHelloService(AfterEmailProviderService afterEmailProviderService, AfterEnvironmentService afterEnvironmentService) {
        this.afterEmailProviderService = afterEmailProviderService;
        this.afterEnvironmentService = afterEnvironmentService;
    }

    public CompletableFuture<String> sayHello(long userId) {
        CompletableFuture<String> emailResponse = afterEmailProviderService.getEmail(userId);

        CompletableFuture<String> helloResponse = emailResponse.thenApply(new Function<String, String>() {
            public String apply(String email) {
                return "Hello " + email + " and welcome on " + afterEnvironmentService.getServerName();
            }
        });

        return helloResponse;
    }
}