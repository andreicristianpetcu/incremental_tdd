package com.andreicristianpetcu.incrementaltdd.before.service;

import com.andreicristianpetcu.incrementaltdd.before.common.Callback;

import java.util.concurrent.atomic.AtomicReference;

public class BeforePrivateInfoDivCreatorService {

    private final BeforePrivateInfoService beforePrivateInfoService;

    public BeforePrivateInfoDivCreatorService(BeforePrivateInfoService beforePrivateInfoService) {
        this.beforePrivateInfoService = beforePrivateInfoService;
    }

    public void generatePersonalInfoDiv(long userId, final Callback<String> callback) {
        final AtomicReference<String> socialSecurityNumberRef = new AtomicReference<String>();
        final AtomicReference<String> fullNameRef = new AtomicReference<String>();

        beforePrivateInfoService.getSocialSecurityNumber(userId, new Callback<String>() {
            public void done(String socialSecurityNumber) {
                String fullName = fullNameRef.get();
                if (fullName != null) {
                    String resultingDiv = computeDiv(socialSecurityNumber, fullName);
                    callback.done(resultingDiv);
                } else {
                    socialSecurityNumberRef.set(socialSecurityNumber);
                }
            }
        });
        beforePrivateInfoService.getFullName(userId, new Callback<String>() {
            public void done(String fullName) {
                String socialSecurityNumber = socialSecurityNumberRef.get();
                if (socialSecurityNumber != null) {
                    String resultingDiv = computeDiv(socialSecurityNumber, fullName);
                    callback.done(resultingDiv);
                } else {
                    fullNameRef.set(fullName);
                }
            }
        });
    }

    private String computeDiv(String socialSecurityNumber, String fullName) {
        return "<div>" + fullName.toUpperCase() + " - " + socialSecurityNumber + "</div>";
    }
}