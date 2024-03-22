package base;

import com.github.javafaker.Faker;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import GUI.pages.LoginPage;
import GUI.pages.WelcomePage;

import java.util.Properties;

import static com.codeborne.selenide.Selenide.*;

public class BaseTest {
    protected static final String configFilePath = "src/test/resources/configuration.properties";
    protected static Properties properties = new Properties();
    protected static String baseUrl;
    protected static String configUserName;
    protected static String apiKey;
    protected static String token;
    protected static String loginEmail;
    protected static String loginPassword;
    protected static final Faker faker = new Faker();
    protected static RequestSpecification reqSpec;
    protected static ResponseSpecification respSpec;

    protected void loginToApp() {
        WelcomePage welcomePage = page(WelcomePage.class);
        LoginPage loginPage = page(LoginPage.class);
        welcomePage.logInButton.click();
        loginPage.login.setValue(loginEmail);
        loginPage.loginSubmitButton.click();
        loginPage.password.setValue(loginPassword);
        loginPage.loginSubmitButton.click();
    }
}