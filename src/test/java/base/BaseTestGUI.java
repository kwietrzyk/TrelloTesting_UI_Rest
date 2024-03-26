package base;

import GUI.pages.main.LoginPage;
import GUI.pages.main.MainPage;
import GUI.pages.main.WelcomePage;
import GUI.pages.boardMenu.BoardPage;
import GUI.pages.boardMenu.ClosingBoardPage;
import GUI.pages.createNewBoard.NewBoardPage;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class BaseTestGUI extends BaseTestREST {
    private final WelcomePage welcomePage = page(WelcomePage.class);
    private final LoginPage loginPage = page(LoginPage.class);
    protected final MainPage mainpage = page(MainPage.class);
    protected final NewBoardPage newBoardPage = page(NewBoardPage.class);
    protected final BoardPage boardPage = page(BoardPage.class);
    protected final ClosingBoardPage closingBoardPage = page(ClosingBoardPage.class);

    @BeforeAll
    protected static void setupGui() {
        loginEmail = PROPERTIES.getProperty("email");
        loginPassword = PROPERTIES.getProperty("password");
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true));
    }

    @BeforeEach
    protected void goToApp() {
        open(baseUrl);
        loginToApp();
    }

    @AfterEach
    protected void clearBrowser() {
        clearBrowserCookies();
        clearBrowserLocalStorage();
        closeWindow();
    }

    protected void loginToApp() {
        welcomePage.logInButton.click();
        setUserEmail();
        setUserPassword();
    }

    private void setUserEmail() {
        loginPage.login.setValue(loginEmail);
        loginPage.loginSubmitButton.click();
    }

    private void setUserPassword() {
        loginPage.password.setValue(loginPassword);
        loginPage.loginSubmitButton.click();
    }

    protected void createNewBoard() {
        mainpage.createNewTableButton.shouldBe(visible).click();
        newBoardPage.title.setValue(MY_NEW_TABLE);
        newBoardPage.submit.click();
        mainpage.allBoards.click();
    }

    protected void removeNewBoard() {
        refresh();
        boardPage.settings.shouldBe(visible).click();
        boardPage.closeBoard.scrollTo().click();
        closingBoardPage.closingSubmitButton.click();
        closingBoardPage.deletePermanently.click();
        closingBoardPage.submitPermanentDelete.click();
        mainpage.allBoards.click();
    }
}
