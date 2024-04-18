package rest.helpers;

import gui.dto.boardDto.main.BoardDto;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import rest.endpointsobjects.Board;
import rest.endpointsobjects.ListTrello;

import java.util.List;
import java.util.Map;

public final class RestInternalHelper extends RestHelper {

    @Step("Creating new board with name {boardName}")
    public String createNewBoardAndFetchId(String boardName) {
        return API_CLIENT.postNewBoard(boardName).execute().jsonPath().get("id");
    }

    @Step("Creating new board with queryMap")
    public String createNewBoardWithQueryMapAndFetchId(Map<String, String> queryMap) {
        return API_CLIENT.postNewBoard(queryMap).execute().jsonPath().get("id");
    }

    @Step("Create new list: {name}")
    public String createNewListAndFetchId(String name, String boardId) {
        return API_CLIENT.postNewListToBoard(name, boardId).execute().jsonPath().get("id");
    }

    @Step("Create new card: {cardName} on list {list.listDto.name}")
    public String createNewCardAndFetchId(String cardName, ListTrello list) {
        return API_CLIENT.postNewCardToList(cardName, list.getListDto().getId()).execute().jsonPath().get("id");
    }

    @Step("Move list {listId} to board {dstBoardId}")
    public Response moveList(String listId, String dstBoardId) {
        return API_CLIENT.moveList(listId, dstBoardId).execute();
    }

    @Step("Update board")
    public void updateBoard(BoardDto dto) {
        API_CLIENT.putBoard(dto).execute();
    }

    @Step("Update board")
    public void updateBoard(String boardId, Map<String, String> query) {
        API_CLIENT.putBoard(boardId, query).execute();
    }

    @Step("Update list field {fieldName}")
    public void updateListField(String listId, String fieldName, String newName) {
        API_CLIENT.updateListField(listId, fieldName, newName).execute();
    }

    @Step("Deleting all boards")
    public void deleteAllBoards() {
        List<String> boardsToDelete = getAllBoardsIds();
        deleteAllBoardsFromIdList(boardsToDelete);
    }

    @Step("Deleting all boards with name: {boardName}")
    public void deleteAllBoardsWithName(String boardName) {
        List<String> boardsToDelete = getBoardsIdByBoardName(boardName);
        deleteAllBoardsFromIdList(boardsToDelete);
    }

    @Step("Deleting boards found in the list")
    public void deleteAllBoardsFromIdList(List<String> boardsIdList) {
        for (String boardId : boardsIdList) {
            deleteBoard(boardId);
        }
    }

    @Step("Delete board {boardId}")
    public Response deleteBoard(String boardId) {
        return API_CLIENT.deleteBoard(boardId).execute();
    }

    @Step("Delete list {list.listDto.name}")
    public Response deleteList(ListTrello list) {
        return API_CLIENT.deleteList(list.getListDto().getId()).execute();
    }

}
