package tests.rest;

import rest.endpointsobjects.Board;
import rest.endpointsobjects.ListTrello;
import common.enums.BoardListsNames;
import rest.helpers.BoardManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.base.BaseTest;

import java.util.List;

import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ListTest extends BaseTest {

    // In this class actions are done on endpoint objects and verification is done by restHelper methods

    @Test
    @DisplayName("Move list to another board")
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
    @DisplayName("Translate default list names to english")
    @Tag("rest")
    @Tag("list")
    public void shouldChangeListsNames() {
        Board board1 = BoardManager.createBoard("Board 1");
        for (ListTrello list : board1.getLists()) {
            list.translateDefaultNameToEnglish();
        }
        restHelper.getAllListsFromBoard(board1.getBoardDto().getId()).then()
                .body("name", hasItem(BoardListsNames.TODO.getEnglishLabel()))
                .body("name", hasItem(BoardListsNames.ONGOING.getEnglishLabel()))
                .body("name", hasItem(BoardListsNames.DONE.getEnglishLabel()));
    }

    @Test
    @DisplayName("Remove all lists from board")
    @Tag("rest")
    @Tag("list")
    public void shouldRemoveAllListsFromBoard() {
        Board board = BoardManager.createBoard();
        board.removeAllLists();
        assertTrue(restHelper.getAllListsNamesFromBoard(board).isEmpty());
    }

    @Test
    @DisplayName("Remove single list from board")
    @Tag("rest")
    @Tag("list")
    public void shouldRemoveSingleListFromBoard() {
        Board board = BoardManager.createBoard();
        board.removeLists(BoardListsNames.TODO.getPolishLabel());
        assertEquals(DEFAULT_LISTS_AMOUNT - 1, restHelper.getAllListsNamesFromBoard(board).size());
    }

    @Test
    @DisplayName("Add new list")
    @Tag("rest")
    @Tag("list")
    public void shouldAddNewList() {
        Board board = BoardManager.createBoard();
        String newListName = BoardListsNames.ONHOLD.getPolishLabel();
        board.createList(newListName);
        assertTrue(restHelper.getAllListsNamesFromBoard(board).contains(newListName));
    }

}
