package rest.requestobjects.requests.list;

import io.restassured.RestAssured;
import rest.requestobjects.requests.BaseRequest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;

public class PostNewListToBoard extends BaseRequest {
    public PostNewListToBoard(String listName, String idBoard, RequestSpecBuilder requestSpecBuilder) {
        this.requestSpecBuilder = requestSpecBuilder;
        this.requestSpecBuilder.addQueryParam("name", listName);
        this.requestSpecBuilder.addQueryParam("idBoard", idBoard);
    }

    @Override
    public Response execute() {
        return RestAssured.given().spec(requestSpecBuilder.build()).when().post("/1/lists");
    }
}
