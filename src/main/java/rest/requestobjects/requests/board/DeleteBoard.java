package rest.requestobjects.requests.board;

import io.restassured.RestAssured;
import rest.requestobjects.requests.BaseRequest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;

public class DeleteBoard extends BaseRequest {
    public DeleteBoard(String boardId, RequestSpecBuilder requestSpecBuilder) {
        this.requestSpecBuilder = requestSpecBuilder;
        this.requestSpecBuilder.addPathParam("boardId", boardId);
    }

    @Override
    public Response execute() {
        return RestAssured.given().spec(requestSpecBuilder.build()).when().delete("/1/boards/{boardId}");
    }
}
