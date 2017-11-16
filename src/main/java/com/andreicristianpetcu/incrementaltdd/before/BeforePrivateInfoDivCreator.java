package com.andreicristianpetcu.incrementaltdd.before;

public class BeforePrivateInfoDivCreator {

    private final BeforePrivateInfoService beforePrivateInfoService;

    public BeforePrivateInfoDivCreator(BeforePrivateInfoService beforePrivateInfoService) {
        this.beforePrivateInfoService = beforePrivateInfoService;
    }

    public void generatePersonalInfoDiv(long userId, final String fullName, final Callback<String> callback) {
        beforePrivateInfoService.getSocialSecurityNumber(userId, new Callback<String>() {
            public void done(String socialSecurityNumber) {
                String resultingDiv = computeDiv(socialSecurityNumber, fullName);
                callback.done(resultingDiv);
            }
        });
    }

    private String computeDiv(String socialSecurityNumber, String fullName) {
        return "<div>" + fullName.toUpperCase() + " - " + socialSecurityNumber + "</div>";
    }
}