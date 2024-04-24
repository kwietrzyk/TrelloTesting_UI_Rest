package tests.rest;

import rest.dto.boardDto.main.BoardDto;
import io.qameta.allure.Step;
import net.bytebuddy.utility.RandomString;
import rest.endpointsobjects.Board;
import rest.factories.BoardQueryFactory;
import rest.helpers.BoardManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.base.BaseTest;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest extends BaseTest {

    // In this class actions are done on endpoint objects and verification is done by RestHelper methods
    // Lists created by default: "Do zrobienia", "W trakcie", "Zrobione"

    private String boardName = RandomString.make();

    @Test
    @DisplayName("TC: Create new board by Name, verify and remove")
    @Tag("rest")
    @Tag("board")
    public void shouldCreateNewBoardAndRemoveItByRestApi() {
        int initialBoardsAmount = restHelper.getAmountOfCurrentBoards();
        Board board = BoardManager.createBoard(boardName);
        assertEquals(restHelper.getAmountOfCurrentBoards(), initialBoardsAmount + 1, "Amount of boards did not increment after POST");
        BoardManager.deleteBoard(board);
        assertEquals(restHelper.getAmountOfCurrentBoards(), initialBoardsAmount,
                "Amount of boards did not decrement after DELETE");
    }

    @Test
    @DisplayName("TC: Create new board by queryMap, verify and remove")
    @Tag("rest")
    @Tag("board")
    public void shouldCreateNewBoardByQueryMap() {
        createBoardByQueryAndVerify();
        assertEquals(1, restHelper.getAllBoardsIds().size());
    }

    @Test
    @DisplayName("TC: Create max amount of boards (10)")
    @Tag("rest")
    @Tag("board")
    public void shouldCreateMaxAmountOfBoards() {
        final int maxAmountOfBoards = 10;
        for (int i = 0; i < maxAmountOfBoards; i++) {
            createBoardByQueryAndVerify();
        }
        assertEquals(maxAmountOfBoards, restHelper.getAllBoardsIds().size());
    }

    @Test
    @DisplayName("TC: Create more than max amount of boards")
    @Tag("rest")
    @Tag("board")
    public void shouldNotCreateMoreThanMaxAmountOfBoards() {
        final int maxAmountOfBoards = 10;
        for (int i = 0; i < maxAmountOfBoards; i++) {
            createBoardByQueryAndVerify();
        }
        restHelper.verifyExcessiveBoardFailure("Additional Name");
        assertEquals(maxAmountOfBoards, restHelper.getAllBoardsIds().size());
    }

    @Test
    @DisplayName("TC: Update board")
    @Tag("rest")
    @Tag("board")
    public void shouldUpdateBoard() {
        Board board = BoardManager.createBoard();
        Map<String, String> queryMap = BoardQueryFactory.createPutQueryMap();
        board.updateBoard(queryMap);
        restHelper.verifyBoardParamsAreSet(board, queryMap);
    }

    @Test
    @DisplayName("TC: Update board name")
    @Tag("rest")
    @Tag("board")
    public void shouldUpdateBoardName() {
        Board board = BoardManager.createBoard();
        String newName = "New name";
        board.updateBoard(newName);
        assertTrue(restHelper.isBoardExisting(newName));
    }

    @Test
    @DisplayName("TC: Update board name with passing DTO")
    @Tag("rest")
    @Tag("board")
    public void shouldChangeBoardName() {
        Board board = BoardManager.createBoard(boardName);
        BoardDto boardDto = board.getBoardDto();
        String boardNewName = "New Name";
        boardDto.setName(boardNewName);
        board.updateBoard(boardDto);
        assertTrue(restHelper.isBoardExisting(boardNewName), "Board " + boardNewName + " doest not exist");
    }

    @Step("Create board by query map and verify")
    private void createBoardByQueryAndVerify() {
        Map<String, String> queryMap = BoardQueryFactory.createPostQueryMap();
        Board board = BoardManager.createBoardFromQuery(queryMap);
        restHelper.verifyBoardParamsAreSet(board, queryMap);
    }
}
