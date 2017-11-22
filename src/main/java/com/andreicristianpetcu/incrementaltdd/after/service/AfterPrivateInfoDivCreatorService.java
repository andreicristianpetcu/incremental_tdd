package com.andreicristianpetcu.incrementaltdd.after.service;

import java8.util.concurrent.CompletableFuture;
import java8.util.function.BiFunction;

public class AfterPrivateInfoDivCreatorService {

    private final AfterPrivateInfoService afterPrivateInfoService;

    public AfterPrivateInfoDivCreatorService(AfterPrivateInfoService afterPrivateInfoService) {
        this.afterPrivateInfoService = afterPrivateInfoService;
    }

    CompletableFuture<String> generatePersonalInfoDiv(long userId) {
        CompletableFuture<String> socialSecurityNumber = afterPrivateInfoService.getSocialSecurityNumber(userId);
        CompletableFuture<String> fullName = afterPrivateInfoService.getFullName(userId);

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