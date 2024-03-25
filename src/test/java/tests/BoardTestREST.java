package tests;

import base.BaseTestREST;
import boardDto.main.BoardDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.codeborne.selenide.Selenide.page;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BoardTestREST extends BaseTestREST {

    private static final String KITCHEN_NAME = "Kitchen building";
    private static final String KITCHEN_ID = "65fbf1f4fe067466eef1f32d";

    @Test
    @DisplayName("TC1: CREATE new board, verify and remove - REST")
    @Tag("rest")
    public void shouldCreateNewBoardAndRemoveItByRestApi() {
        int initialBoardsAmount = getNumberOfCurrentBoards();
        apiClient.postNewBoard(MY_NEW_TABLE).execute();
        assertTrue(getNumberOfCurrentBoards() == initialBoardsAmount + 1,
                "Amount of boards did not increment after POST");
        deleteAllMyNewTableBoards();
        assertTrue(getNumberOfCurrentBoards() == initialBoardsAmount,
                "Amount of boards did not decrement after DELETE");
    }

    @Test
    @DisplayName("GET board and convert it to DTO")
    @Tag("rest")
    public void shouldGetBoardAndCreateDtoObject() {
        BoardDto boardDto = apiClient.getBoard(KITCHEN_ID).execute().then().extract().as(BoardDto.class);
        assertTrue(boardDto.name.equals(KITCHEN_NAME), "Deserialization failed");
    }

    @Test
    @DisplayName("GET all lists from board")
    @Tag("rest")
    public void shouldGetAllListsFromBoardAndSaveToList() {
        int expectedNumberOfLists = 3;
        List<String> lists = apiClient.getAllListsFromBoard(KITCHEN_ID).execute().jsonPath().getList("name");
        assertTrue(lists.size() == expectedNumberOfLists, "Unexpected number of lists");
    }

    @Test
    @DisplayName("DELETE all MyNewTable boards")
    @Tag("rest")
    public void shouldRemoveAllMyNewTableBoards() {
        deleteAllMyNewTableBoards();
        assertFalse(isMyNewTableCreated(), MY_NEW_TABLE + " is not removed");
    }

    private int getNumberOfCurrentBoards() {
        return apiClient.getAllBoards(configUserName).execute().jsonPath().getList("boards").size();
    }

    private boolean isMyNewTableCreated() {
        return apiClient.getAllBoards(configUserName).execute()
                .jsonPath().get("name").equals(MY_NEW_TABLE);
    }
}
