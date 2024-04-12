package tests.rest;

import helpers.RestHelper;
import net.bytebuddy.utility.RandomString;
import tests.base.BaseTest;
import dto.boardDto.main.BoardDto;
import factories.BoardQueryFactory;
import org.junit.jupiter.api.*;

import java.util.Map;

import static helpers.RestHelper.*;
import static org.junit.jupiter.api.Assertions.*;

public class BoardRestTest extends BaseTest {

    // In this class REST requests are called directly by static methods of RestHelper
    private String boardName = RandomString.make();
    private String boardId;

    @Test
    @DisplayName("TC: Create new board by Name, verify and remove")
    @Tag("rest")
    public void shouldCreateNewBoardAndRemoveItByRestApi() {
        int initialBoardsAmount = getAmountOfCurrentBoards();
        boardId = createNewBoardAndFetchId(boardName);
        assertTrue(getAmountOfCurrentBoards() == initialBoardsAmount + 1,
                "Amount of boards did not increment after POST");
        deleteBoard(boardId);
        assertEquals(getAmountOfCurrentBoards(), initialBoardsAmount,
                "Amount of boards did not decrement after DELETE");
    }

    @Test
    @DisplayName("TC: Create new board by queryMap, verify and remove")
    @Tag("rest")
    public void shouldCreateNewBoardByQueryMap() {
        createBoardByQueryAndVerify();
        assertEquals(1, RestHelper.getAllBoardsIds().size());
    }

    @Test
    @DisplayName("Create max amount of boards (10)")
    @Tag("rest")
    public void shouldCreateMaxAmountOfBoards() {
        final int maxAmountOfBoards = 10;
        for (int i = 0; i < maxAmountOfBoards; i++) {
            createBoardByQueryAndVerify();
        }
        assertEquals(maxAmountOfBoards, RestHelper.getAllBoardsIds().size());
    }

    @Test
    @DisplayName("Create more than max amount of boards)")
    @Tag("rest")
    public void shouldNotCreateMoreThanMaxAmountOfBoards() {
        final int maxAmountOfBoards = 10;
        for (int i = 0; i < maxAmountOfBoards; i++) {
            createBoardByQueryAndVerify();
        }
        String additionalBoardId = createNewBoardAndFetchId("Additional board");
        assertNull(additionalBoardId);
        assertEquals(maxAmountOfBoards, RestHelper.getAllBoardsIds().size());
    }

    @Test
    @DisplayName("TC: Update board name with passing DTO")
    @Tag("rest")
    public void shouldChangeBoardName() {
        boardId = createNewBoardAndFetchId(boardName);
        BoardDto boardDto = getBoardDto(boardId);
        String boardNewName = "New Name";
        boardDto.setName(boardNewName);
        updateBoard(boardDto);
        assertTrue(isBoardExisting(boardNewName), "Board " + boardNewName + " doest not exist");
        deleteBoard(boardId);
    }

    @Test
    @DisplayName("TC: Update board with query params map")
    @Tag("rest")
    public void shouldUpdateBoardWithQuery() {
        boardId = createNewBoardAndFetchId(boardName);
        Map<String, String> queryMap = BoardQueryFactory.createPutQueryMap();
        updateBoard(boardId, queryMap);
        verifyBoardParamsAreSet(boardId, queryMap);
        deleteBoard(boardId);
    }

    @Test
    @Disabled("Test can be run to clean unhandled boards")
    @DisplayName("DELETE all boards")
    @Tag("rest")
    public void shouldRemoveAllBoards() {
        deleteAllBoards();
        assertTrue(getAllBoards().isEmpty());
    }

    private void createBoardByQueryAndVerify() {
        Map<String, String> queryMap = BoardQueryFactory.createPostQueryMap();
        String boardId = createNewBoardWithQueryMapAndFetchId(queryMap);
        verifyBoardParamsAreSet(boardId, queryMap);
    }
}
