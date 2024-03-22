package tests;

import DTO.BoardDto;
import REST_framework.client.ApiClient;
import base.BaseTestREST;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.codeborne.selenide.Selenide.page;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BoardTestREST extends BaseTestREST {

    private final String newBoardName = "MyNewTable";
    private ApiClient apiClient = createApiClient();
    private String kitchenId = "65fbf1f4fe067466eef1f32d";


    @Test
    @DisplayName("TC1: Create new board, verify and remove - REST")
    @Tag("rest")
    public void shouldCreateNewBoardAndRemoveItByRestApi() {

        int initialBoardsAmount = apiClient.getAllBoards(configUserName).execute().jsonPath().getList("boards").size();
        System.out.println("Amount of boards when test starts: " + initialBoardsAmount);

        String boardId = apiClient.postNewBoard(newBoardName).execute().jsonPath().get("id");
        int boardsAmountAfterPost = apiClient.getAllBoards(configUserName).execute().jsonPath().getList("boards").size();
        System.out.println("Amount of boards after POST: " + boardsAmountAfterPost);
        assertTrue(boardsAmountAfterPost == initialBoardsAmount + 1, "Unexpected amount of boards");

        apiClient.deleteBoard(boardId).execute();
        int boardsAmountAfterDelete = apiClient.getAllBoards(configUserName).execute().jsonPath().getList("boards").size();
        System.out.println("Amount of boards after DELETE: " + boardsAmountAfterDelete);
        assertTrue(boardsAmountAfterDelete == initialBoardsAmount, "Unexpected amount of boards");
    }

    @Test
    @DisplayName("GET board and convert it to DTO")
    @Tag("rest")
    public void shouldGetBoardAndCreateDtoObject() {
        kitchenId = apiClient.getAllBoards(configUserName).execute().jsonPath().get("[0].id");
        System.out.println(kitchenId);

        //apiClient.getAllBoards(configUserName).execute().then().extract().as(BoardDto.class);
    }

    @Test
    @DisplayName("GET all lists from board")
    @Tag("rest")
    public void shouldGetAllListsFromBoardAndSaveToList() {
        List<String> listNames = apiClient.getAllListsFromBoard(kitchenId).execute().jsonPath().getList("name");
        System.out.println(listNames);
    }
}
