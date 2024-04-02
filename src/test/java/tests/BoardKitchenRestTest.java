package tests;

import base.BaseTestREST;
import boardDto.main.BoardDto;
import io.qameta.allure.Step;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BoardKitchenRestTest extends BaseTestREST {

    private static final String KITCHEN_NAME = "Kitchen building";
    private static String kitchenId;

    @BeforeEach
    @Step("Setup Kitchen Project")
    public void setKitchenProject() {
        List<String> boardIds = getBoardsIdByBoardName(KITCHEN_NAME);
        kitchenId = boardIds.isEmpty() ? createNewBoardAndFetchId(KITCHEN_NAME) : boardIds.get(0);
    }

    @Test
    @DisplayName("GET board and convert it to DTO")
    @Tag("rest")
    public void shouldGetBoardAndCreateDtoObject() {
        BoardDto boardDto = apiClient.getBoard(kitchenId).execute().then().extract().as(BoardDto.class);
        assertTrue(boardDto.name.equals(KITCHEN_NAME), "Deserialization failed");
    }

    @Test
    @DisplayName("GET all lists from board")
    @Tag("rest")
    public void shouldGetAllListsFromBoardAndSaveToList() {
        int expectedNumberOfLists = 3;
        List<String> lists = apiClient.getAllListsFromBoard(kitchenId).execute().jsonPath().getList("name");
        assertTrue(lists.size() == expectedNumberOfLists, "Unexpected number of lists");
    }

}
