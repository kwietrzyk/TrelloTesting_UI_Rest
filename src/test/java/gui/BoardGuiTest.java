package gui;

import gui.pages.boardMenu.BackgroundSettingsPage;
import net.bytebuddy.utility.RandomString;
import rest.endpointsobjects.Board;
import rest.helpers.BoardManager;
import base.BaseTestGUI;
import common.enums.BoardBackgroundColors;
import io.qameta.allure.Description;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class BoardGuiTest extends BaseTestGUI {

    private final BackgroundSettingsPage backgroundSettingsPage = page(BackgroundSettingsPage.class);

    @Test
    @DisplayName("TC: Create new board")
    @Tag("gui")
    @Tag("board")
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
    @Tag("board")
    public void shouldRemoveExistingBoard() {
        String boardName = RandomString.make();
        BoardManager.createBoard(boardName);
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
    @Tag("board")
    public void shouldChangeBoardName() {
        String oldName = RandomString.make();
        BoardManager.createBoard(oldName);
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
    @Tag("board")
    public void shouldUpdateBoardBackgroundToImage() {
        String boardName = "ImageTable";
        Board board = BoardManager.createBoard(boardName);
        mainpage.openBoard(boardName)
                .openBoardSettings()
                .changeBackground()
                .toImage()
                .closeBackgroundSettingPage();
        restHelper.verifyImageIsSetAsBackground(board);
    }

    @Test
    @DisplayName("TC: Update board background to color")
    @Description("Board is created and verified by REST request")
    @Tag("gui")
    @Tag("board")
    public void shouldUpdateBoardBackgroundToColor() {
        String boardName = "ColorTable";
        Board board = BoardManager.createBoard(boardName);
        BoardBackgroundColors backgroundColor = BoardBackgroundColors.getRandom();
        mainpage.openBoard(boardName)
                .openBoardSettings()
                .changeBackground()
                .toColor(backgroundColor)
                .closeBackgroundSettingPage();
        restHelper.verifyColorIsSetAsBackground(board, backgroundColor);
    }
}
