package tests;

import base.BaseTestREST;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BoardNewTableRestTest extends BaseTestREST {

    @Test
    @DisplayName("TC1: CREATE new board, verify and remove - REST")
    @Tag("rest")
    public void shouldCreateNewBoardAndRemoveItByRestApi() {
        int initialBoardsAmount = getAmountOfCurrentBoards();
        createNewBoardAndFetchId(MY_NEW_TABLE);
        assertTrue(getAmountOfCurrentBoards() == initialBoardsAmount + 1,
                "Amount of boards did not increment after POST");
        fetchAndDeleteAllBoardsWithName(MY_NEW_TABLE);
        assertTrue(getAmountOfCurrentBoards() == initialBoardsAmount,
                "Amount of boards did not decrement after DELETE");
    }

    @Test
    @Disabled("Test can be run to clean unhandled MY_NEW_TABLE boards")
    @DisplayName("DELETE all MyNewTable boards")
    @Tag("rest")
    public void shouldRemoveAllMyNewTableBoards() {
        fetchAndDeleteAllBoardsWithName(MY_NEW_TABLE);
        assertFalse(isMyNewTableCreated(MY_NEW_TABLE), MY_NEW_TABLE + " is not removed");
    }
}
