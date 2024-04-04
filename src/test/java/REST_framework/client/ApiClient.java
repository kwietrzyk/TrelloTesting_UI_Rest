package REST_framework.client;

import REST_framework.requests.board.*;
import REST_framework.requests.member.GetBoardsThatMemberBelongsTo;
import dto.boardDto.main.BoardDto;
import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;

import java.util.Map;
import java.util.function.Supplier;

public class ApiClient {
    private final Supplier<RequestSpecBuilder> reqSpecBuilder;

    public ApiClient(Supplier<RequestSpecBuilder> reqSpecBuilder) {
        this.reqSpecBuilder = reqSpecBuilder;
    }

    @Step("Get board with id {id}")
    public GetBoard getBoard(String id) {
        return new GetBoard(id, reqSpecBuilder.get());
    }

    @Step("Delete board with id {id}")
    public DeleteBoard deleteBoard(String id) {
        return new DeleteBoard(id, reqSpecBuilder.get());
    }

    @Step("POST board with name {name}")
    public PostNewBoard postNewBoard(String name) {
        return new PostNewBoard(name, reqSpecBuilder.get());
    }

    @Step("POST board with query map")
    public PostNewBoard postNewBoard(Map<String, String> queryMap) {
        return new PostNewBoard(queryMap, reqSpecBuilder.get());
    }

    @Step("Get all lists from board with id {id}")
    public GetAllListsFromBoard getAllListsFromBoard(String id) { return new GetAllListsFromBoard(id, reqSpecBuilder.get()); }

    @Step("Get {userName}'s boards")
    public GetBoardsThatMemberBelongsTo getAllBoards(String userName) {
        return new GetBoardsThatMemberBelongsTo(userName, reqSpecBuilder.get());
    }

    @Step("Update board")
    public UpdateBoard putBoard(BoardDto dto) {
        return new UpdateBoard(dto, reqSpecBuilder.get());
    }

    @Step("Update board with id {boardId}")
    public UpdateBoard putBoard(String boardId, Map<String, String> query) {
        return new UpdateBoard(boardId, query, reqSpecBuilder.get());
    }
}
