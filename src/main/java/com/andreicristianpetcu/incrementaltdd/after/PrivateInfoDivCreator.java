package com.andreicristianpetcu.incrementaltdd.after;

import java8.util.concurrent.CompletableFuture;
import java8.util.function.Function;

public class PrivateInfoDivCreator {

    private final PrivateInfoService privateInfoService;

    public PrivateInfoDivCreator(PrivateInfoService privateInfoService) {
        this.privateInfoService = privateInfoService;
    }

    public CompletableFuture<String> generatePersonalInfoDiv(long userId, final String fullName) {
        return privateInfoService
                .getSocialSecurityNumber(userId)
                .thenApply(new Function<String, String>() {
                    public String apply(String socialSecurityNumber) {
                        return computeDiv(socialSecurityNumber, fullName);
                    }
                });
    }

    private String computeDiv(String socialSecurityNumber, String fullName) {
        return "<div>" + fullName.toUpperCase() + " - "+ socialSecurityNumber + "</div>";
    }
}