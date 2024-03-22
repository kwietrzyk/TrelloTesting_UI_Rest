package REST_framework.requests.board;

import REST_framework.requests.BaseRequest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class DeleteBoard extends BaseRequest {
    public DeleteBoard(String boardId, RequestSpecBuilder requestSpecBuilder) {
        this.requestSpecBuilder = requestSpecBuilder;
        this.requestSpecBuilder.addPathParam("boardId", boardId);
    }

    @Override
    public Response execute() {
        return given().spec(requestSpecBuilder.build()).when().delete("/1/boards/{boardId}");
    }
}
