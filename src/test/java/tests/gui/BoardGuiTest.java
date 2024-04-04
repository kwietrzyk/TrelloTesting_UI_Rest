package tests.gui;

import GUI.pages.boardMenu.BackgroundSettingsPage;
import base.BaseTestGUI;
import enums.BoardBackgroundColors;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static helpers.RestHelper.*;
import static org.junit.jupiter.api.Assertions.*;

public class BoardGuiTest extends BaseTestGUI {

    private final BackgroundSettingsPage backgroundSettingsPage = page(BackgroundSettingsPage.class);

    @Test
    @DisplayName("TC1: Create new board, verify and remove - GUI")
    @Tag("gui")
    public void shouldCreateNewBoardAndRemoveIt() {
        createNewBoard();
        mainpage.myNewTableBoard.shouldBe(visible);
        removeNewBoard();
        mainpage.myNewTableBoard.shouldNot(exist);
    }

    @Test
    @DisplayName("TC2: Change board name")
    @Description("Board is created, verified and deleted by REST actions to speed up stable actions")
    @Tag("gui")
    public void shouldChangeBoardName() {
        String boardId = createNewBoardAndFetchId(MY_NEW_TABLE);
        String boardNewName = "New Name";
        changeBoardName(boardNewName);
        assertTrue(isBoardExisting(boardNewName), "Board " + boardNewName + " doest not exist");
        deleteBoard(boardId);
    }

    @Test
    @DisplayName("TC3: Update board background")
    @Description("Board is created, verified and deleted by REST actions to speed up stable actions")
    @Tag("gui")
    public void shouldUpdateBoardBackground() {
        String boardId = createNewBoardAndFetchId(MY_NEW_TABLE);
        setBackgroundAsPhoto();
        verifyImageIsSetAsBackground(boardId);

        BoardBackgroundColors backgroundColor = BoardBackgroundColors.getRandom();
        setBackgroundAsColor(backgroundColor);
        verifyColorIsSetAsBackground(boardId, backgroundColor);
        deleteBoard(boardId);
    }

    @Step("Create new board")
    private void createNewBoard() {
        mainpage.goToMainPage.click();
        mainpage.createNewTableButton.shouldBe(visible).click();
        newBoardPage.title.shouldBe(visible).setValue(MY_NEW_TABLE);
        newBoardPage.submit.click();
        sleep(2000);
        mainpage.goToMainPage.click();
    }

    @Step("Remove board")
    private void removeNewBoard() {
        mainpage.goToMainPage.click();
        mainpage.myNewTableBoard.shouldBe(visible).click();
        boardPage.settings.shouldBe(visible).click();
        boardSettingsPage.closeBoard.scrollTo().click();
        closingBoardPage.closingSubmitButton.click();
        closingBoardPage.deletePermanently.click();
        closingBoardPage.submitPermanentDelete.click();
        mainpage.goToMainPage.click();
    }

    @Step("Change board name to {boardNewName}")
    private void changeBoardName(String boardNewName) {
        refresh();
        mainpage.myNewTableBoard.shouldBe(visible).click();
        boardPage.nameDisplay.shouldBe(editable).click();
        executeJavaScript(String.format("arguments[0].value = '';", boardNewName), boardPage.nameInput);
        boardPage.nameInput.setValue(boardNewName);
        mainpage.goToMainPage.click();
        $(byText(boardNewName)).shouldBe(visible);
    }

    @Step("Set background as photo")
    private void setBackgroundAsPhoto() {
        goToBackgroundMenu();
        backgroundSettingsPage.photos.shouldBe(visible).click();
        backgroundSettingsPage.backgroundBox.scrollTo().click();
        closeBackgroundSettingPage();
    }

    @Step("Set background as color {color}")
    private void setBackgroundAsColor(BoardBackgroundColors color) {
        goToBackgroundMenu();
        backgroundSettingsPage.colors.shouldBe(visible).click();
        backgroundSettingsPage.chooseColorElement(color).click();
        closeBackgroundSettingPage();
    }

    @Step("Go to main page")
    private void goToBackgroundMenu() {
        refresh();
        mainpage.goToMainPage.shouldBe(visible).click();
        mainpage.myNewTableBoard.shouldBe(visible).click();
        boardPage.settings.shouldBe(visible).click();
        boardSettingsPage.changeBackground.shouldBe(visible).click();
    }

    private void closeBackgroundSettingPage() {
        backgroundSettingsPage.photoMenuClosingButton.shouldBe(visible).click();
        refresh();
        sleep(1500);
    }
}
