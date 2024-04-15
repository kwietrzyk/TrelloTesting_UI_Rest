package rest.requestobjects.requests.list;

import io.restassured.RestAssured;
import rest.requestobjects.requests.BaseRequest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;

public class UpdateListField extends BaseRequest {
    public UpdateListField(String listId, String fieldName, String fieldnewValue, RequestSpecBuilder requestSpecBuilder) {
        this.requestSpecBuilder = requestSpecBuilder;
        this.requestSpecBuilder.addPathParam("listId", listId);
        this.requestSpecBuilder.addPathParam("field", fieldName);
        this.requestSpecBuilder.addQueryParam("value", fieldnewValue);
    }

    @Override
    public Response execute() {
        return RestAssured.given().spec(requestSpecBuilder.build()).when().put("/1/lists/{listId}/{field}");
    }
}
