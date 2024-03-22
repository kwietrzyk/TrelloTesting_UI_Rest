package base;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.io.FileInputStream;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.*;

public class BaseTestGUI extends BaseTest {

    @BeforeAll
    public static void baseSetup() throws IOException {
        properties.load(new FileInputStream(configFilePath));
        baseUrl = properties.getProperty("app.url");
        configUserName = properties.getProperty("userName");
        loginEmail = properties.getProperty("email");
        loginPassword = properties.getProperty("password");
    }

    @BeforeEach
    public void setup() {
        open(baseUrl);
        loginToApp();
    }

    @AfterEach
    public void clearBrowser() {
        clearBrowserCookies();
        clearBrowserLocalStorage();
    }
}
