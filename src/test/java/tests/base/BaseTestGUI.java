package tests.base;

import GUI.pages.boardMenu.BoardSettingsPage;
import GUI.pages.main.LoginPage;
import GUI.pages.main.MainPage;
import GUI.pages.main.WelcomePage;
import GUI.pages.boardMenu.BoardPage;
import GUI.pages.boardMenu.ClosingBoardPage;
import GUI.pages.createNewBoard.NewBoardPage;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import configuration.TestConfiguration;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import helpers.RestHelper;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.*;

public class BaseTestGUI extends BaseTest {
    private final static WelcomePage welcomePage = page(WelcomePage.class);
    private final static LoginPage loginPage = page(LoginPage.class);
    protected final static MainPage mainpage = page(MainPage.class);
    protected final static NewBoardPage newBoardPage = page(NewBoardPage.class);
    protected final static BoardPage boardPage = page(BoardPage.class);
    protected final static BoardSettingsPage boardSettingsPage = page(BoardSettingsPage.class);
    protected final static ClosingBoardPage closingBoardPage = page(ClosingBoardPage.class);

    @BeforeAll
    @Step("Setup GUI")
    protected static void setupGui() {
        setSelenideConfiguration();
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true));

    }

    @BeforeEach
    @Step("Setup clear main page")
    protected void setupClearMainPage() {
        goToApp();
        RestHelper.deleteAllBoards();
        sleep(1000);
        refresh();
    }

    private static void setSelenideConfiguration() {
        Configuration.timeout = 10000;
        Configuration.browser = Browsers.EDGE.getName();   // Chrome is set as default
    }

    protected static void goToApp() {
        open(TestConfiguration.baseUrl);
        WebDriverRunner.getWebDriver().manage().window().maximize();
        loginToApp();
    }

    protected static void loginToApp() {
        welcomePage.logInButton.click();
        setUserEmail();
        setUserPassword();
    }

    private static void setUserEmail() {
        loginPage.login.setValue(TestConfiguration.loginEmail);
        loginPage.loginSubmitButton.click();
    }

    private static void setUserPassword() {
        loginPage.password.setValue(TestConfiguration.loginPassword);
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
