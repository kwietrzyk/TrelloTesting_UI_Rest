package tests.rest;

import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.BeforeEach;
import rest.endpointsobjects.Board;
import rest.endpointsobjects.ListTrello;
import common.enums.BoardListsNames;
import rest.helpers.BoardManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.base.BaseTest;

import java.util.List;
import java.util.Random;

import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ListTest extends BaseTest {

    // In this class actions are done on endpoint objects and verification is done by restHelper methods

    private Board board;

    @BeforeEach
    public void createTestBoard() {
        final String boardName = RandomString.make();
        board = BoardManager.createBoard(boardName);
    }

    @Test
    @DisplayName("TC: Move list to another board")
    @Tag("rest")
    @Tag("list")
    public void shouldMoveListFromOneBoardToAnother() {
        Board board1 = BoardManager.createBoard("Board 1");
        Board board2 = BoardManager.createBoard("Board 2");
        String newListName = "My list";
        board1.createList(newListName);
        board1.getList(newListName).moveToBoard(board2);
        List<String> board1Lists = restHelper.getAllListsNamesFromBoard(board1);
        List<String> board2Lists = restHelper.getAllListsNamesFromBoard(board2);
        assertTrue(board1Lists.size() == DEFAULT_LISTS_AMOUNT && board2Lists.contains(newListName));
    }

    @Test
    @DisplayName("TC: Translate default list names to english")
    @Tag("rest")
    @Tag("list")
    public void shouldChangeListsNames() {
        for (ListTrello list : board.getLists()) {
            list.translateDefaultNameToEnglish();
        }
        restHelper.getAllListsFromBoard(board.getBoardDto().getId()).then()
                .body("name", hasItem(BoardListsNames.TODO.getEnglishLabel()))
                .body("name", hasItem(BoardListsNames.ONGOING.getEnglishLabel()))
                .body("name", hasItem(BoardListsNames.DONE.getEnglishLabel()));
    }

    @Test
    @DisplayName("TC: Remove all lists from board")
    @Tag("rest")
    @Tag("list")
    public void shouldRemoveAllListsFromBoard() {
        board.removeAllLists();
        assertTrue(restHelper.getAllListsNamesFromBoard(board).isEmpty());
    }

    @Test
    @DisplayName("TC: Remove single list from board")
    @Tag("rest")
    @Tag("list")
    public void shouldRemoveSingleListFromBoard() {
        board.removeLists(BoardListsNames.getRandomDefaultName());
        assertEquals(DEFAULT_LISTS_AMOUNT - 1, restHelper.getAllListsNamesFromBoard(board).size());
    }

    @Test
    @DisplayName("TC: Add new list to board")
    @Tag("rest")
    @Tag("list")
    public void shouldAddNewList() {
        String newListName = BoardListsNames.ONHOLD.getPolishLabel();
        board.createList(newListName);
        assertTrue(restHelper.getAllListsNamesFromBoard(board).contains(newListName));
    }

    @Test
    @DisplayName("TC: Create cards on default lists")
    @Tag("rest")
    @Tag("list")
    public void shouldCreateCardsOnDefaultLists() {
        int expectedNumberOfCards = new Random().nextInt(1, 10);
        for (ListTrello list : board.getLists()) {
            list.createMultipleCards(expectedNumberOfCards);
        }
        for (ListTrello list : board.getLists()) {
            int cardsAmount = restHelper.getAllCardsFromList(list).jsonPath().getList("").size();
            assertEquals(expectedNumberOfCards, cardsAmount, "Amount of cards is not as expected on list " + list.getListDto().getName());
        }
    }

}
