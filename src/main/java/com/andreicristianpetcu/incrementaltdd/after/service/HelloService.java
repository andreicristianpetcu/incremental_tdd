package com.andreicristianpetcu.incrementaltdd.after.service;

import java8.util.concurrent.CompletableFuture;
import java8.util.function.Function;

public class HelloService {

    private final EmailProviderService emailProviderService;
    private final EnvironmentService environmentService;

    public HelloService(EmailProviderService emailProviderService, EnvironmentService environmentService) {
        this.emailProviderService = emailProviderService;
        this.environmentService = environmentService;
    }

    public CompletableFuture<String> sayHello(long userId) {
        CompletableFuture<String> emailResponse = emailProviderService.getEmail(userId);

        CompletableFuture<String> helloResponse = emailResponse.thenApply(new Function<String, String>() {
            public String apply(String email) {
                return "Hello " + email + " and welcome on " + environmentService.getServerName();
            }
        });

        return helloResponse;
    }
}