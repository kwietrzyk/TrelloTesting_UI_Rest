package rest.requestobjects.requests.list;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import rest.requestobjects.requests.BaseRequest;

public class MoveListToBoard extends BaseRequest {
    public MoveListToBoard(String listId, String idDestinationBoard, RequestSpecBuilder requestSpecBuilder) {
        this.requestSpecBuilder = requestSpecBuilder;
        this.requestSpecBuilder.addPathParam("listId", listId);
        this.requestSpecBuilder.addQueryParam("value", idDestinationBoard);
    }

    @Override
    public Response execute() {
        return RestAssured.given().spec(requestSpecBuilder.build()).when().put("/1/lists/{listId}/idBoard");
    }
}
