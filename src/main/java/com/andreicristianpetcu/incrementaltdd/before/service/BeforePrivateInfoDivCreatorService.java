package com.andreicristianpetcu.incrementaltdd.before.service;

import com.andreicristianpetcu.incrementaltdd.before.common.Callback;

public class BeforePrivateInfoDivCreatorService {

    private final BeforePrivateInfoService beforePrivateInfoService;

    public BeforePrivateInfoDivCreatorService(BeforePrivateInfoService beforePrivateInfoService) {
        this.beforePrivateInfoService = beforePrivateInfoService;
    }

    void generatePersonalInfoDiv(final long userId, final Callback<String> callback) {
        beforePrivateInfoService.getSocialSecurityNumber(userId, new Callback<String>() {
            public void done(final String socialSecurityNumber) {
                beforePrivateInfoService.getFullName(userId, new Callback<String>() {
                    public void done(String fullName) {
                        String resultingDiv = computeDiv(socialSecurityNumber, fullName);
                        callback.done(resultingDiv);
                    }
                });
            }
        });
    }

    private String computeDiv(String socialSecurityNumber, String fullName) {
        return "<div>" + fullName.toUpperCase() + " - " + socialSecurityNumber + "</div>";
    }
}