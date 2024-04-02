package base;

import GUI.pages.main.LoginPage;
import GUI.pages.main.MainPage;
import GUI.pages.main.WelcomePage;
import GUI.pages.boardMenu.BoardPage;
import GUI.pages.boardMenu.ClosingBoardPage;
import GUI.pages.createNewBoard.NewBoardPage;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class BaseTestGUI extends BaseTestREST {
    private final static WelcomePage welcomePage = page(WelcomePage.class);
    private final static LoginPage loginPage = page(LoginPage.class);
    protected final MainPage mainpage = page(MainPage.class);
    protected final NewBoardPage newBoardPage = page(NewBoardPage.class);
    protected final BoardPage boardPage = page(BoardPage.class);
    protected final ClosingBoardPage closingBoardPage = page(ClosingBoardPage.class);

    @BeforeAll
    @Step("Setup GUI")
    protected static void setupGui() {
        loginEmail = PROPERTIES.getProperty("email");
        loginPassword = PROPERTIES.getProperty("password");
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true));
        goToApp();
    }

    protected static void goToApp() {
        Configuration.timeout = 10000;
        open(baseUrl);
        loginToApp();
    }

    protected static void loginToApp() {
        welcomePage.logInButton.click();
        setUserEmail();
        setUserPassword();
    }

    private static void setUserEmail() {
        loginPage.login.setValue(loginEmail);
        loginPage.loginSubmitButton.click();
    }

    private static void setUserPassword() {
        loginPage.password.setValue(loginPassword);
        loginPage.loginSubmitButton.click();
    }

    @AfterEach()
    @Description("Verify if any MyNewTable exists and remove it")
    protected void cleanMainPage() {
        mainpage.boards.click();
        if (mainpage.myNewTableBoard.exists()) {
            fetchAndDeleteAllBoardsWithName(MY_NEW_TABLE);
        }
    }

    @AfterAll
    protected static void clearBrowser() {
        clearBrowserCookies();
        clearBrowserLocalStorage();
        closeWindow();
    }

    protected void createNewBoard() {
        mainpage.boards.click();
        mainpage.createNewTableButton.shouldBe(visible).click();
        newBoardPage.title.shouldBe(visible).setValue(MY_NEW_TABLE);
        newBoardPage.submit.click();
        sleep(2000);
        mainpage.allBoards.click();
    }

    protected void removeNewBoard() {
        refresh();
        mainpage.myNewTableBoard.shouldBe(visible).click();
        boardPage.settings.shouldBe(visible).click();
        boardPage.closeBoard.scrollTo().click();
        closingBoardPage.closingSubmitButton.click();
        closingBoardPage.deletePermanently.click();
        closingBoardPage.submitPermanentDelete.click();
        mainpage.allBoards.click();
    }
}
