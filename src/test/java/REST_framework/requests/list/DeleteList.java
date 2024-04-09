package REST_framework.requests.list;

import REST_framework.requests.BaseRequest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class DeleteList extends BaseRequest {
    public DeleteList(String listId, RequestSpecBuilder requestSpecBuilder) {
        this.requestSpecBuilder = requestSpecBuilder;
        this.requestSpecBuilder.addPathParam("listId", listId);
        this.requestSpecBuilder.addQueryParam("value", true);
    }

    @Override
    public Response execute() {
        return given().spec(requestSpecBuilder.build()).when().put("/1/lists/{listId}/closed");
    }
}
