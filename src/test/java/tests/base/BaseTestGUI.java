package tests.base;

import gui.pages.preconditions.LoginPage;
import gui.pages.main.MainPage;
import gui.pages.preconditions.WelcomePage;
import gui.pages.boardMenu.BoardPage;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import common.configuration.TestConfiguration;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import rest.helpers.RestInternalHelper;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.*;

public class BaseTestGUI extends BaseTest {
    private final static WelcomePage welcomePage = page(WelcomePage.class);
    private final static LoginPage loginPage = page(LoginPage.class);
    protected final static MainPage mainpage = page(MainPage.class);
    protected final static BoardPage boardPage = page(BoardPage.class);

    @BeforeAll
    @Step("Setup GUI")
    protected static void setupGui() {
        setSelenideConfiguration();
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true));

    }

    private static void setSelenideConfiguration() {
        Configuration.timeout = 10000;
        Configuration.browser = TestConfiguration.BROWSER;
    }

    // Needs Selenium grid configuration
    private static void setRemoteHub() {
        Configuration.remote = "http://localhost:4444/wd/hub";
    }

    @BeforeEach
    @Step("Setup clear main page")
    public void goToApp() {
        open(TestConfiguration.BASE_URL);
        WebDriverRunner.getWebDriver().manage().window().maximize();
        loginToApp();
    }

    protected static void loginToApp() {
        welcomePage.logInButton.click();
        setUserEmail();
        setUserPassword();
    }

    private static void setUserEmail() {
        loginPage.login.setValue(TestConfiguration.LOGIN_EMAIL);
        loginPage.loginSubmitButton.click();
    }

    private static void setUserPassword() {
        loginPage.password.setValue(TestConfiguration.LOGIN_PASSWORD);
        loginPage.loginSubmitButton.click();
    }

    @AfterEach
    @Step("Teardown")
    protected void clearBrowser() {
        clearBrowserCookies();
        clearBrowserLocalStorage();
        Selenide.closeWindow();
    }
}
