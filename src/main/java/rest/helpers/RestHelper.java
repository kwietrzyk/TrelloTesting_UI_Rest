package rest.helpers;

import common.configuration.TestConfiguration;
import common.enums.BoardBackgroundColors;
import gui.dto.boardDto.main.BoardDto;
import gui.dto.cardDto.CardDto;
import gui.dto.listDto.ListDto;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.assertj.core.api.SoftAssertions;
import org.hamcrest.Matchers;
import rest.endpointsobjects.Board;
import rest.endpointsobjects.Card;
import rest.endpointsobjects.ListTrello;
import rest.requestobjects.client.ApiClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public sealed class RestHelper permits RestInternalHelper {

    protected static final ApiClient API_CLIENT = TestConfiguration.API_CLIENT;
    protected static final String USERNAME = TestConfiguration.CONFIG_USER_NAME;

    @Step("Get boardDTO for board {boardId}")
    public BoardDto getBoardDto(String boardId) {
        return getBoard(boardId).then().extract().as(BoardDto.class);
    }

    @Step("Get board {boardId}")
    public Response getBoard(String boardId) {
        return API_CLIENT.getBoard(boardId).execute();
    }

    @Step("Getting all boards as list")
    public List<Map<String, Object>> getAllBoards() {
        return API_CLIENT.getAllBoards(USERNAME).execute().jsonPath().getList("");
    }

    @Step("Getting all boards IDs as list")
    public List<String> getAllBoardsIds() {
        return API_CLIENT.getAllBoards(USERNAME).execute().jsonPath().getList("id");
    }

    @Step("Getting amount of boards")
    public int getAmountOfCurrentBoards() {
        return API_CLIENT.getAllBoards(USERNAME).execute().jsonPath().getList("boards").size();
    }

    @Step("Get listDTO for list")
    public ListDto getListDto(String listId) {
        return getList(listId).then().extract().as(ListDto.class);
    }

    @Step("Get list")
    public Response getList(String listId) {
        return API_CLIENT.getList(listId).execute();
    }

    @Step("Get list of lists from board {board.boardDto.name}")
    public List<String> getAllListsNamesFromBoard(Board board) {
        return getAllListsFromBoard(board.getBoardDto().getId()).jsonPath().getList("name");
    }

    @Step("Get all lists from board {boardId}")
    public Response getAllListsFromBoard(String boardId) {
        return API_CLIENT.getAllListsFromBoard(boardId).execute();
    }

    @Step("Get cardDTO for card")
    public CardDto getCardDto(String cardId) {
        return getCard(cardId).then()
                .extract().as(CardDto.class);
    }

    @Step("Get card")
    public Response getCard(String cardId) {
        return API_CLIENT.getCard(cardId).execute();
    }

    @Step("Verification if {boardName} exists")
    public boolean isBoardExisting(String boardName) {
        Response response =API_CLIENT.getAllBoards(USERNAME).execute();
        return response
                .jsonPath().getList("name").contains(boardName);
    }

    @Step("Verification if background of board {board.boardDto.name} is set to Photo")
    public void verifyImageIsSetAsBackground(Board board) {
        Response response = getBoard(board.getBoardDto().getId());
        response.then() // .log().body() - uncomment for debugging purpose
                .body("prefs.backgroundColor", Matchers.nullValue())
                .body("prefs.backgroundImage", Matchers.notNullValue());
    }

    @Step("Verification if background of board {board.boardDto.name} is set to color {color}")
    public void verifyColorIsSetAsBackground(Board board, BoardBackgroundColors color) {
        Response response = getBoard(board.getBoardDto().getId());
        response.then() // .log().body() - uncomment for debugging purpose
                .body("prefs.background", Matchers.equalTo(color.getLabel()))
                .body("prefs.backgroundImage", Matchers.nullValue());
    }

    @Step("Board attributes verification")
    public void verifyBoardParamsAreSet(Board board, Map<String, String> expectedQueryMap) {
        Response response = getBoard(board.getBoardDto().getId());
        SoftAssertions softAssert = new SoftAssertions();
        for (Map.Entry<String, String> query : expectedQueryMap.entrySet()) {
            String key = query.getKey().replaceAll("[/_]", ".");
            softAssert.assertThat(response.then().body(key, Matchers.equalTo(query.getValue())));
        }
        softAssert.assertAll();
    }

    @Step("Verification that additional board cannot be created")
    public void verifyExcessiveBoardFailure(String boardName) {
        Response response = API_CLIENT.postNewBoard(boardName).execute();
        response.then()
                .body("id", Matchers.nullValue())
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Step("Getting boards IDs for boards with name {boardName}")
    public List<String> getBoardsIdByBoardName(String boardName) {
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

    @Step("Verification if {cardName} is on list {list.listDto.name}")
    public boolean isCardOnList(String cardName, ListTrello list) {
        return getAllCardsFromList(list).jsonPath().getList("name").contains(cardName);
    }

    @Step("Get all cards from list {list.listDto.name}")
    public Response getAllCardsFromList(ListTrello list) {
        return API_CLIENT.getAllCardsFromList(list.getListDto().getId()).execute();
    }

}
