package com.andreicristianpetcu.incrementaltdd.before.service;

import com.andreicristianpetcu.incrementaltdd.before.common.Callback;

public class PrivateInfoDivCreatorService {

    private final PrivateInfoService privateInfoService;

    public PrivateInfoDivCreatorService(PrivateInfoService privateInfoService) {
        this.privateInfoService = privateInfoService;
    }

    void generatePersonalInfoDiv(final long userId, final Callback<String> callback) {
        privateInfoService.getSocialSecurityNumber(userId, new Callback<String>() {
            public void done(final String socialSecurityNumber) {
                privateInfoService.getFullName(userId, new Callback<String>() {
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