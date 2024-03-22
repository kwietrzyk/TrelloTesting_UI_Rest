package GUI.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    public SelenideElement login = $("#username");
    public SelenideElement loginSubmitButton = $("#login-submit");
    public SelenideElement unhidePassword = $(".css-1spmf3f");
    public SelenideElement password = $("#password");
}
