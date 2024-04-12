package REST_framework.requests.list;

import REST_framework.requests.BaseRequest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UpdateListField extends BaseRequest {
    public UpdateListField(String listId, String fieldName, String fieldnewValue, RequestSpecBuilder requestSpecBuilder) {
        this.requestSpecBuilder = requestSpecBuilder;
        this.requestSpecBuilder.addPathParam("listId", listId);
        this.requestSpecBuilder.addPathParam("field", fieldName);
        this.requestSpecBuilder.addQueryParam("value", fieldnewValue);
    }

    @Override
    public Response execute() {
        return given().spec(requestSpecBuilder.build()).when().put("/1/lists/{listId}/{field}");
    }
}
