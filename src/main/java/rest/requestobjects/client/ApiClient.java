package rest.requestobjects.client;

import rest.requestobjects.requests.board.*;
import rest.requestobjects.requests.card.*;
import rest.requestobjects.requests.list.*;
import rest.requestobjects.requests.member.GetBoardsThatMemberBelongsTo;
import rest.dto.boardDto.main.BoardDto;
import io.restassured.builder.RequestSpecBuilder;

import java.util.Map;
import java.util.function.Supplier;

public class ApiClient {
    public final Supplier<RequestSpecBuilder> reqSpecBuilder;
    public ApiClient(Supplier<RequestSpecBuilder> reqSpecBuilder) {
        this.reqSpecBuilder = reqSpecBuilder;
    }

    // BOARD
    public GetBoard getBoard(String id) {
        return new GetBoard(id, reqSpecBuilder.get());
    }

    public DeleteBoard deleteBoard(String id) {
        return new DeleteBoard(id, reqSpecBuilder.get());
    }

    public PostNewBoard postNewBoard(String name) {
        return new PostNewBoard(name, reqSpecBuilder.get());
    }

    public PostNewBoard postNewBoard(Map<String, String> queryMap) {
        return new PostNewBoard(queryMap, reqSpecBuilder.get());
    }

    public UpdateBoard putBoard(BoardDto dto) {
        return new UpdateBoard(dto, reqSpecBuilder.get());
    }

    public UpdateBoard putBoard(String boardId, Map<String, String> query) {
        return new UpdateBoard(boardId, query, reqSpecBuilder.get());
    }

    public GetAllListsFromBoard getAllListsFromBoard(String id) { return new GetAllListsFromBoard(id, reqSpecBuilder.get()); }


    // LIST
    public PostNewListToBoard postNewListToBoard(String listName, String idBoard) { return new PostNewListToBoard(listName, idBoard, reqSpecBuilder.get()); }

    public GetList getList(String listId) {
        return new GetList(listId, reqSpecBuilder.get());
    }

    public DeleteList deleteList(String listId) {
        return new DeleteList(listId, reqSpecBuilder.get());
    }

    public MoveListToBoard moveList(String listId, String dstBoardId) {
        return new MoveListToBoard(listId, dstBoardId, reqSpecBuilder.get());
    }

    public UpdateListField updateListField(String listId, String fieldName, String newName) {
        return new UpdateListField(listId, fieldName, newName, reqSpecBuilder.get());
    }

    public GetAllCardsFromList getAllCardsFromList(String listId) {
        return new GetAllCardsFromList(listId, reqSpecBuilder.get());
    }


    // CARD
    public PostNewCardToList postNewCardToList(String cardName, String listId) {
        return new PostNewCardToList(cardName, listId, reqSpecBuilder.get());
    }

    public GetCard getCard(String cardId) {
        return new GetCard(cardId, reqSpecBuilder.get());
    }

    public MoveCardToList moveCard(String cardId, String dstListId) {
        return new MoveCardToList(cardId, dstListId, reqSpecBuilder.get());
    }

    public UpdateCard updateCard(String cardId, Map<String, String> queryParams) {
        return new UpdateCard(cardId, queryParams, reqSpecBuilder.get());
    }

    public DeleteCard deleteCard(String cardId) {
        return new DeleteCard(cardId, reqSpecBuilder.get());
    }

    public AddCommentToCard addCommentToCard(String cardId, String comment) {
        return new AddCommentToCard(cardId, comment, reqSpecBuilder.get());
    }


    // MEMBER
    public GetBoardsThatMemberBelongsTo getAllBoards(String userName) {
        return new GetBoardsThatMemberBelongsTo(userName, reqSpecBuilder.get());
    }

}
