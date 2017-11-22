package com.andreicristianpetcu.incrementaltdd.before.service;

import com.andreicristianpetcu.incrementaltdd.before.common.Callback;
import com.andreicristianpetcu.incrementaltdd.before.model.User;

public class PrivateInfoDivCreatorService {

    private final PrivateInfoService privateInfoService;

    public PrivateInfoDivCreatorService(PrivateInfoService privateInfoService) {
        this.privateInfoService = privateInfoService;
    }

    void generatePersonalInfoDiv(final User user, final Callback<String> callback) {
        privateInfoService.getSocialSecurityNumber(user.getId(), new Callback<String>() {
            public void done(final String socialSecurityNumber) {
                privateInfoService.getFullName(user.getId(), new Callback<String>() {
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