package rest.requestobjects.requests.board;

import io.restassured.RestAssured;
import rest.requestobjects.requests.BaseRequest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;

public class GetAllListsFromBoard extends BaseRequest {
    public GetAllListsFromBoard(String boardId, RequestSpecBuilder requestSpecBuilder) {
        this.requestSpecBuilder = requestSpecBuilder;
        this.requestSpecBuilder.addPathParam("boardId", boardId);
    }

    @Override
    public Response execute() {
        return RestAssured.given().spec(requestSpecBuilder.build()).when().get("/1/boards/{boardId}/lists");
    }

}
