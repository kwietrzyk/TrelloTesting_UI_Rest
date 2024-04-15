package rest.requestobjects.requests.list;

import io.restassured.RestAssured;
import rest.requestobjects.requests.BaseRequest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;

public class GetList extends BaseRequest {
    public GetList(String listId, RequestSpecBuilder requestSpecBuilder) {
        this.requestSpecBuilder = requestSpecBuilder;
        this.requestSpecBuilder.addPathParam("listId", listId);
    }

    @Override
    public Response execute() {
        return RestAssured.given().spec(requestSpecBuilder.build()).when().get("/1/lists/{listId}");
    }
}
