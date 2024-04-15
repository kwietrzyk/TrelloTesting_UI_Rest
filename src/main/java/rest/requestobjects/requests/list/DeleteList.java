package rest.requestobjects.requests.list;

import io.restassured.RestAssured;
import rest.requestobjects.requests.BaseRequest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;

public class DeleteList extends BaseRequest {
    public DeleteList(String listId, RequestSpecBuilder requestSpecBuilder) {
        this.requestSpecBuilder = requestSpecBuilder;
        this.requestSpecBuilder.addPathParam("listId", listId);
        this.requestSpecBuilder.addQueryParam("value", true);
    }

    @Override
    public Response execute() {
        return RestAssured.given().spec(requestSpecBuilder.build()).when().put("/1/lists/{listId}/closed");
    }
}
