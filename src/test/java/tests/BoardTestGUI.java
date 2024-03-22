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
    MainPage mainpage = page(MainPage.class);
    NewBoardPage newBoardPage = page(NewBoardPage.class);
    BoardPage boardPage = page(BoardPage.class);
    ClosingBoardPage closingBoardPage = page(ClosingBoardPage.class);
    private final String newBoardName = "MyNewTable";

    @Test
    @DisplayName("TC1: Create new board, verify and remove - GUI")
    @Tag("gui")
    // This test is behaving different than manual one - refresh loads to newBoard page when it should stay at main
    public void shouldCreateNewBoardAndRemoveIt() {

        mainpage.createNewTableButton.shouldBe(visible).click();
        newBoardPage.title.setValue(newBoardName);
        newBoardPage.submit.click();
        mainpage.allBoards.click();
        assertTrue(mainpage.myNewTableBoard.shouldBe(visible).isDisplayed());
        refresh();

        boardPage.settings.shouldBe(visible).click();
        boardPage.closeBoard.scrollTo().click();
        closingBoardPage.closingSubmitButton.click();
        closingBoardPage.deletePermanently.click();
        closingBoardPage.submitPermanentDelete.click();

        mainpage.allBoards.click();
        assertFalse(mainpage.myNewTableBoard.exists());
    }
}
