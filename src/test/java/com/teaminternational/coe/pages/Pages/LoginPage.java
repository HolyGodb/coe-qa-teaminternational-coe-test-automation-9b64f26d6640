package com.teaminternational.coe.pages.Pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    public SelenideElement loginPageUsername = $("[data-test=username]"),
    loginPagePassword = $("[data-test=password]"),
    loginPageSubmitButton = $(".login-button");

    public void loginMethod(String username, String password) {
        loginPageUsername.setValue(username);
        loginPagePassword.setValue(password);
        loginPageSubmitButton.click();
    }
}
