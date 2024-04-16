package rest.requestobjects.requests.list;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import rest.requestobjects.requests.BaseRequest;

public class GetAllCardsFromList extends BaseRequest {
    public GetAllCardsFromList(String listId, RequestSpecBuilder requestSpecBuilder) {
        this.requestSpecBuilder = requestSpecBuilder;
        this.requestSpecBuilder.addPathParam("listId", listId);
    }

    @Override
    public Response execute() {
        return RestAssured.given().spec(requestSpecBuilder.build()).when().get("/1/lists/{listId}/cards");
    }
}
