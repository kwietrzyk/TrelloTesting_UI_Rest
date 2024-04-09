package REST_framework.requests.list;

import REST_framework.requests.BaseRequest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GetList extends BaseRequest {
    public GetList(String listId, RequestSpecBuilder requestSpecBuilder) {
        this.requestSpecBuilder = requestSpecBuilder;
        this.requestSpecBuilder.addPathParam("listId", listId);
    }

    @Override
    public Response execute() {
        return given().spec(requestSpecBuilder.build()).when().get("/1/lists/{listId}");
    }
}
