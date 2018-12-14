import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    SelenideElement loginPageUsername = $("[data-test=username]");
    SelenideElement loginPagePassword = $("[data-test=password]");
    SelenideElement loginPageSubmitButton = $(".login-button");

    public void loginMethod(String username, String password) {
        loginPageUsername.setValue(username);
        loginPagePassword.setValue(password);
        loginPageSubmitButton.click();
    }
}
