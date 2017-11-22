package com.andreicristianpetcu.incrementaltdd.after.service;

import java8.util.concurrent.CompletableFuture;
import java8.util.function.BiFunction;

public class PrivateInfoDivCreatorService {

    private final PrivateInfoService privateInfoService;

    public PrivateInfoDivCreatorService(PrivateInfoService privateInfoService) {
        this.privateInfoService = privateInfoService;
    }

    CompletableFuture<String> generatePersonalInfoDiv(long userId) {
        CompletableFuture<String> socialSecurityNumber = privateInfoService.getSocialSecurityNumber(userId);
        CompletableFuture<String> fullName = privateInfoService.getFullName(userId);

        CompletableFuture<String> generatedPersonalInfo = socialSecurityNumber.thenCombine(fullName,
                new BiFunction<String, String, String>() {
            public String apply(String socialSecurityNumber, String fullName) {
                return computeDiv(socialSecurityNumber, fullName);
            }
        });

        return generatedPersonalInfo;
    }

    private String computeDiv(String socialSecurityNumber, String fullName) {
        return "<div>" + fullName.toUpperCase() + " - "+ socialSecurityNumber + "</div>";
    }
}