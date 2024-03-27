package base;

import REST_framework.client.ApiClient;
import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BaseTestREST extends BaseTest {

    protected ApiClient apiClient = createApiClient();
    protected static final String MY_NEW_TABLE =  "MyNewTable";

    @Step("Creating API Client")
    protected ApiClient createApiClient() {
        return new ApiClient(() -> new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .setContentType(ContentType.JSON)
                .addQueryParam("key", apiKey)
                .addQueryParam("token", token)
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter()));
    }

    @Step("Creating new board with name {boardName}")
    protected String createNewBoardAndFetchId(String boardName) {
        return apiClient.postNewBoard(boardName).execute().jsonPath().get("id");
    }

    @Step("Deleting all boards with name: {boardName}")
    protected void fetchAndDeleteAllBoardsWithName(String boardName) {
        List<String> boardsIdList = getBoardsIdByBoardName(boardName);
        deleteAllBoardsFromIdList(boardsIdList);
    }

    @Step("Getting all boards as list")
    protected List<Map<String, Object>> getAllBoardsList() {
        return apiClient.getAllBoards(configUserName).execute().jsonPath().getList("");
    }

    @Step("Deleting boards found in the list")
    protected void deleteAllBoardsFromIdList(List<String> boardsIdList) {
        for (String boardId : boardsIdList) {
            apiClient.deleteBoard(boardId).execute();
        }
    }

    @Step("Getting boards IDs for boards with name {boardName}")
    protected List<String> getBoardsIdByBoardName(String boardName) {
        List<Map<String, Object>> boardsList = getAllBoardsList();
        List<String> idsList = new ArrayList<>();
        for (Map<String, Object> board : boardsList) {
            String name = board.get("name").toString();
            if (name.equals(boardName)) {
                idsList.add(board.get("id").toString());
            }
        }
        return idsList;
    }

    @Step("Getting amount of boards")
    protected int getAmountOfCurrentBoards() {
        return apiClient.getAllBoards(configUserName).execute().jsonPath().getList("boards").size();
    }

    @Step("Verification if {boardName} exists")
    protected boolean isMyNewTableCreated(String boardName) {
        return apiClient.getAllBoards(configUserName).execute()
                .jsonPath().get("name").equals(boardName);
    }
}
