package REST_framework.requests.board;

import REST_framework.client.ExecutableRequest;
import REST_framework.requests.BaseRequest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UpdateBoard extends BaseRequest {

    public UpdateBoard(String boardId, Object body, RequestSpecBuilder requestSpecBuilder) {
        this.requestSpecBuilder = requestSpecBuilder;
        this.requestSpecBuilder.addPathParam("boardId", boardId);
        this.requestSpecBuilder.setBody(body);
    }

    @Override
    public Response execute() {
        return given().spec(requestSpecBuilder.build()).when().put("/1/boards/{boardId}");
    }
}
