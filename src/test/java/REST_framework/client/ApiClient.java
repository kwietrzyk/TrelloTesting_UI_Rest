package REST_framework.client;

import REST_framework.requests.board.DeleteBoard;
import REST_framework.requests.board.GetAllListsFromBoard;
import REST_framework.requests.board.GetBoard;
import REST_framework.requests.board.PostNewBoard;
import REST_framework.requests.member.GetBoardsThatMemberBelongsTo;
import io.restassured.builder.RequestSpecBuilder;

import java.util.function.Supplier;

public class ApiClient {
    private final Supplier<RequestSpecBuilder> reqSpecBuilder;

    public ApiClient(Supplier<RequestSpecBuilder> reqSpecBuilder) {
        this.reqSpecBuilder = reqSpecBuilder;
    }

    public GetBoard getBoard(String id) {
        return new GetBoard(id, reqSpecBuilder.get());
    }

    public DeleteBoard deleteBoard(String id) {
        return new DeleteBoard(id, reqSpecBuilder.get());
    }

    public PostNewBoard postNewBoard(String name) {
        return new PostNewBoard(name, reqSpecBuilder.get());
    }

    public GetAllListsFromBoard getAllListsFromBoard(String id) { return new GetAllListsFromBoard(id, reqSpecBuilder.get()); }

    public GetBoardsThatMemberBelongsTo getAllBoards(String userName) {
        return new GetBoardsThatMemberBelongsTo(userName, reqSpecBuilder.get());
    }

}
