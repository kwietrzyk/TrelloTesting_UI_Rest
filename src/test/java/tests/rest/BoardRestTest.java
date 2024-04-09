package tests.rest;

import tests.base.BaseTest;
import dto.boardDto.main.BoardDto;
import factories.BoardQueryFactory;
import org.junit.jupiter.api.*;

import java.util.Map;

import static helpers.RestHelper.*;
import static org.junit.jupiter.api.Assertions.*;

public class BoardRestTest extends BaseTest {

    @Test
    @DisplayName("TC: CREATE new board by Name, verify and remove")
    @Tag("rest")
    public void shouldCreateNewBoardAndRemoveItByRestApi() {
        int initialBoardsAmount = getAmountOfCurrentBoards();
        createNewBoardAndFetchId(MY_NEW_TABLE);
        assertTrue(getAmountOfCurrentBoards() == initialBoardsAmount + 1,
                "Amount of boards did not increment after POST");
        deleteAllBoardsWithName(MY_NEW_TABLE);
        assertEquals(getAmountOfCurrentBoards(), initialBoardsAmount,
                "Amount of boards did not decrement after DELETE");
    }

    @Test
    @DisplayName("TC: CREATE new board by queryMap, verify and remove")
    @Tag("rest")
    public void shouldCreateNewBoardByQueryMap() {
        Map<String, String> queryMap = BoardQueryFactory.createPostQueryMap();
        String boardId = createNewBoardWithQueryMapAndFetchId(queryMap);
        verifyBoardParamsAreSet(boardId, queryMap);
        deleteBoard(boardId);
    }

    @Test
    @DisplayName("TC: UPDATE board name with passing DTO")
    @Tag("rest")
    public void shouldChangeBoardName() {
        String boardId = createNewBoardAndFetchId(MY_NEW_TABLE);
        BoardDto boardDto = getBoardDto(boardId);
        String boardNewName = "New Name";
        boardDto.setName(boardNewName);
        updateBoard(boardDto);
        assertTrue(isBoardExisting(boardNewName), "Board " + boardNewName + " doest not exist");
        deleteBoard(boardId);
    }

    @Test
    @DisplayName("TC: UPDATE board with query params map")
    @Tag("rest")
    public void shouldUpdateBoardWithQuery() {
        String boardId = createNewBoardAndFetchId(MY_NEW_TABLE);
        Map<String, String> queryMap = BoardQueryFactory.createPutQueryMap();
        updateBoard(boardId, queryMap);
        verifyBoardParamsAreSet(boardId, queryMap);
        deleteBoard(boardId);
    }

    @Test
    @Disabled("Test can be run to clean unhandled MY_NEW_TABLE boards")
    @DisplayName("DELETE all MyNewTable boards")
    @Tag("rest")
    public void shouldRemoveAllMyNewTableBoards() {
        String tableToDelete = MY_NEW_TABLE;
        deleteAllBoardsWithName(tableToDelete);
        assertFalse(isBoardExisting(tableToDelete), tableToDelete + " is not removed");
    }

    @Test
    @Disabled("Test can be run to clean unhandled boards")
    @DisplayName("DELETE all boards")
    @Tag("rest")
    public void shouldRemoveAllBoards() {
        deleteAllBoards();
        assertTrue(getAllBoards().isEmpty());
    }
}
