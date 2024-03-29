package tests;

import base.BaseTestGUI;
import com.codeborne.selenide.ex.UIAssertionError;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BoardGuiTest extends BaseTestGUI {

    @Test
    @DisplayName("TC1: CREATE new board, verify and remove - GUI")
    @Tag("gui")
    public void shouldCreateNewBoardAndRemoveIt() {
        createNewBoard();
        mainpage.myNewTableBoard.shouldBe(visible);
        try {
            removeNewBoard();
        } catch (UIAssertionError e) {
            logger.warn("Failed to remove board");
            fetchAndDeleteAllBoardsWithName(MY_NEW_TABLE);
        }
        mainpage.myNewTableBoard.shouldNot(exist);
    }

    @Test
    @DisplayName("TC2: UPDATE board with Kitchen Project - GUI")
    @Tag("empty")
    public void shouldUpdateExistingBoard() {

    }
}
