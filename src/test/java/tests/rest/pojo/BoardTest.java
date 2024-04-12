package tests.rest.pojo;

import endpointsObjects.Board;
import factories.BoardFactory;
import enums.BoardListsNames;
import factories.BoardQueryFactory;
import helpers.RestHelper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.base.BaseTest;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BoardTest extends BaseTest {

    // Lists created by default: "Do zrobienia", "W trakcie", "Zrobione"

    @Test
    @DisplayName("Remove all lists from board")
    @Tag("rest")
    @Tag("pojo")
    public void shouldRemoveAllListsFromBoard() {
        Board board = BoardFactory.createBoard();
        board.removeAllLists();
        assertTrue(RestHelper.getAllListsNamesFromBoard(board.getBoardDto().getId()).isEmpty());
    }

    @Test
    @DisplayName("Remove single list from board")
    @Tag("rest")
    @Tag("pojo")
    public void shouldRemoveSingleListFromBoard() {
        Board board = BoardFactory.createBoard();
        board.removeLists(BoardListsNames.TODO.getPolishLabel());
        assertEquals(DEFAULT_LISTS_AMOUNT - 1, RestHelper.getAllListsNamesFromBoard(board.getBoardDto().getId()).size());
    }

    @Test
    @DisplayName("Add new list")
    @Tag("rest")
    @Tag("pojo")
    public void shouldAddNewList() {
        Board board = BoardFactory.createBoard();
        String newListName = BoardListsNames.ONHOLD.getPolishLabel();
        board.createList(newListName);
        assertTrue(RestHelper.getAllListsNamesFromBoard(board.getBoardDto().getId()).contains(newListName));
    }

    @Test
    @DisplayName("Update board")
    @Tag("rest")
    @Tag("pojo")
    public void shouldUpdateBoard() {
        Board board = BoardFactory.createBoard();
        Map<String, String> queryMap = BoardQueryFactory.createPutQueryMap();
        board.updateBoard(BoardQueryFactory.createPutQueryMap());
        RestHelper.verifyBoardParamsAreSet(board.getBoardDto().getId(), queryMap);
    }

    @Test
    @DisplayName("Update board name")
    @Tag("rest")
    @Tag("pojo")
    public void shouldUpdateBoardName() {
        Board board = BoardFactory.createBoard();
        String newName = "New name";
        board.updateBoard(newName);
        assertTrue(RestHelper.isBoardExisting(newName));
    }


}
