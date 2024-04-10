package REST_framework.requests.list;

import REST_framework.requests.BaseRequest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class MoveListToBoard extends BaseRequest {
    public MoveListToBoard(String listId, String idDestinationBoard, RequestSpecBuilder requestSpecBuilder) {
        this.requestSpecBuilder = requestSpecBuilder;
        this.requestSpecBuilder.addPathParam("listId", listId);
        this.requestSpecBuilder.addQueryParam("value", idDestinationBoard);
    }

    @Override
    public Response execute() {
        return given().spec(requestSpecBuilder.build()).when().put("/1/lists/{listId}/idBoard");
    }
}
