package REST_framework.requests.board;

import REST_framework.requests.BaseRequest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GetBoard extends BaseRequest {
    public GetBoard(String boardId, RequestSpecBuilder requestSpecBuilder) {
        this.requestSpecBuilder = requestSpecBuilder;
        this.requestSpecBuilder.addPathParam("boardId", boardId);
    }

    @Override
    public Response execute() {
        return given().spec(requestSpecBuilder.build()).when().get("/1/boards/{boardId}");
    }
}
