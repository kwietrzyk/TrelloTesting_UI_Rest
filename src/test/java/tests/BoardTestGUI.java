package tests;

import base.BaseTestGUI;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import GUI.pages.MainPage;
import GUI.pages.boardMenu.BoardPage;
import GUI.pages.boardMenu.ClosingBoardPage;
import GUI.pages.createNewBoard.NewBoardPage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BoardTestGUI extends BaseTestGUI {


//     This test is not stable. Sometimes it reload pages in unexpected way.
//     In case of unhandled board deletion it is recommended to do it manually
//     or by running shouldRemoveAllMyNewTableBoards from BoardTestRest class
    @Test
    @DisplayName("TC1: CREATE new board, verify and remove - GUI")
    @Tag("gui")
    public void shouldCreateNewBoardAndRemoveIt() {
        createNewBoard();
        assertTrue(mainpage.myNewTableBoard.shouldBe(visible).isDisplayed());
        removeNewBoard();
        assertFalse(mainpage.myNewTableBoard.exists());
    }

    @Test
    @DisplayName("TC2: UPDATE board with Kitchen Project - GUI")
    @Tag("gui")
    public void shouldUpdateExistingBoard() {

    }
}
