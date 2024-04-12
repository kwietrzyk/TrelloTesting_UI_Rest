package tests.gui;

import GUI.pages.boardMenu.BackgroundSettingsPage;
import factories.BoardFactory;
import helpers.RestHelper;
import net.bytebuddy.utility.RandomString;
import tests.base.BaseTestGUI;
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
    @DisplayName("TC: Create new board")
    @Tag("gui")
    public void shouldCreateNewBoard() {
        String newBoardName = RandomString.make();
        mainpage.createNewBoard()
                .withName(newBoardName)
                .submit()
                .goToMainPage()
                .findBoardWithName(newBoardName)
                .shouldBe(visible);
    }

    @Test
    @DisplayName("TC: Remove existing board")
    @Description("Precondition prepared by REST request (creating board)")
    @Tag("gui")
    public void shouldRemoveExistingBoard() {
        String boardName = RandomString.make();
        BoardFactory.createBoard(boardName);
        refresh();
        mainpage.openBoard(boardName)
                .openBoardSettings()
                .closeBoard()
                .deletePermanently()
                .goToMainPage()
                .findBoardWithName(boardName)
                .shouldNot(exist);
    }

    @Test
    @DisplayName("TC: Change board name")
    @Description("Precondition prepared by REST request (creating board)")
    @Tag("gui")
    public void shouldChangeBoardName() {
        String oldName = RandomString.make();
        BoardFactory.createBoard(oldName);
        refresh();
        String newName = "New Name";
        mainpage.openBoard(oldName)
                .changeName()
                .withValue(newName)
                .goToMainPage()
                .findBoardWithName(newName)
                .shouldBe(visible);
    }

    @Test
    @DisplayName("TC: Update board background to image")
    @Description("Board is created and verified by REST request")
    @Tag("gui")
    public void shouldUpdateBoardBackgroundToImage() {
        String boardName = RandomString.make();
        String boardId = createNewBoardAndFetchId(boardName);
        mainpage.openBoard(boardName)
                .openBoardSettings()
                .changeBackground()
                .toImage()
                .closeBackgroundSettingPage();
        verifyImageIsSetAsBackground(boardId);
    }

    @Test
    @DisplayName("TC: Update board background to color")
    @Description("Board is created and verified by REST request")
    @Tag("gui")
    public void shouldUpdateBoardBackgroundToColor() {
        String boardName = RandomString.make();
        String boardId = createNewBoardAndFetchId(boardName);
        BoardBackgroundColors backgroundColor = BoardBackgroundColors.getRandom();
        mainpage.openBoard(boardName)
                .openBoardSettings()
                .changeBackground()
                .toColor(backgroundColor)
                .closeBackgroundSettingPage();
        verifyColorIsSetAsBackground(boardId, backgroundColor);
    }
}
