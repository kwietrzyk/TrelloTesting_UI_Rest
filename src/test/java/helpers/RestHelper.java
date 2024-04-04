package helpers;

import REST_framework.client.ApiClient;
import base.BaseTest;
import configuration.TestConfiguration;
import dto.boardDto.main.BoardDto;
import enums.BoardBackgroundColors;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static org.hamcrest.Matchers.*;

public class RestHelper {

    private RestHelper() {
        throw new IllegalStateException("Instance is not allowed, static methods only");
    }

    private static final ApiClient API_CLIENT = BaseTest.API_CLIENT;
    private static final String USERNAME = TestConfiguration.configUserName;

    @Step("Creating new board with name {boardName}")
    public static String createNewBoardAndFetchId(String boardName) {
        return API_CLIENT.postNewBoard(boardName).execute().jsonPath().get("id");
    }

    @Step("Creating new board with queryMap")
    public static String createNewBoardWithQueryMapAndFetchId(Map<String, String> queryMap) {
        return API_CLIENT.postNewBoard(queryMap).execute().jsonPath().get("id");
    }

    @Step("Get boardDTO for board {boardId}")
    public static BoardDto getBoardDto(String boardId) {
        return getBoard(boardId).then().extract().as(BoardDto.class);
    }

    @Step("Get board {boardId}")
    public static Response getBoard(String boardId) {
        return API_CLIENT.getBoard(boardId).execute();
    }

    @Step("Getting all boards as list")
    public static List<Map<String, Object>> getAllBoards() {
        return API_CLIENT.getAllBoards(USERNAME).execute().jsonPath().getList("");
    }

    @Step("Getting all boards IDs as list")
    public static List<String> getAllBoardsIds() {
        return API_CLIENT.getAllBoards(USERNAME).execute().jsonPath().getList("id");
    }

    @Step("Getting amount of boards")
    public static int getAmountOfCurrentBoards() {
        return API_CLIENT.getAllBoards(USERNAME).execute().jsonPath().getList("boards").size();
    }

    @Step("Get all lists names from board {boardId}")
    public static List<String> getAllListsNames(String boardId) {
        return getAllListsFromBoard(boardId).jsonPath().getList("name");
    }

    @Step("Get all lists from board {boardId}")
    public static Response getAllListsFromBoard(String boardId) {
        return API_CLIENT.getAllListsFromBoard(boardId).execute();
    }

    @Step("Update board")
    public static void updateBoard(BoardDto dto) {
        API_CLIENT.putBoard(dto).execute();
    }

    @Step("Update board")
    public static void updateBoard(String boardId, Map<String, String> query) {
        API_CLIENT.putBoard(boardId, query).execute();
    }

    @Step("Deleting all boards")
    public static void deleteAllBoards() {
        List<String> boardsToDelete = getAllBoardsIds();
        deleteAllBoardsFromIdList(boardsToDelete);
    }

    @Step("Deleting all boards with name: {boardName}")
    public static void deleteAllBoardsWithName(String boardName) {
        List<String> boardsToDelete = getBoardsIdByBoardName(boardName);
        deleteAllBoardsFromIdList(boardsToDelete);
    }

    @Step("Deleting boards found in the list")
    public static void deleteAllBoardsFromIdList(List<String> boardsIdList) {
        for (String boardId : boardsIdList) {
            deleteBoard(boardId);
        }
    }

    @Step("Delete board {boardId}")
    public static void deleteBoard(String boardId) {
        API_CLIENT.deleteBoard(boardId).execute();
    }

    @Step("Verification if {boardName} exists")
    public static boolean isBoardExisting(String boardName) {
        Response response =API_CLIENT.getAllBoards(USERNAME).execute();
        return response
                .jsonPath().getList("name").contains(boardName);
    }

    @Step("Verification if background of board {boardId} is set to Photo")
    public static void verifyImageIsSetAsBackground(String boardId) {
        Response response = getBoard(boardId);
        response.then() // .log().body() - uncomment for debugging purpose
                .body("prefs.backgroundColor", nullValue())
                .body("prefs.backgroundImage", notNullValue());
    }

    @Step("Verification if background of board {boardId} is set to color {color}")
    public static void verifyColorIsSetAsBackground(String boardId, BoardBackgroundColors color) {
        Response response = getBoard(boardId);
        response.then() // .log().body() - uncomment for debugging purpose
                .body("prefs.background", equalTo(color.getLabel()))
                .body("prefs.backgroundImage", nullValue());
    }

    @Step("Getting boards IDs for boards with name {boardName}")
    public static List<String> getBoardsIdByBoardName(String boardName) {
        List<Map<String, Object>> boardsList = getAllBoards();
        List<String> idsList = new ArrayList<>();
        for (Map<String, Object> board : boardsList) {
            String name = board.get("name").toString();
            if (name.equals(boardName)) {
                idsList.add(board.get("id").toString());
            }
        }
        return idsList;
    }
}
