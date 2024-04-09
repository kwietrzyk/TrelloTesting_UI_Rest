package REST_framework.requests.list;

import REST_framework.requests.BaseRequest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PostNewListToBoard extends BaseRequest {
    public PostNewListToBoard(String listName, String idBoard, RequestSpecBuilder requestSpecBuilder) {
        this.requestSpecBuilder = requestSpecBuilder;
        this.requestSpecBuilder.addQueryParam("name", listName);
        this.requestSpecBuilder.addQueryParam("idBoard", idBoard);
    }

    @Override
    public Response execute() {
        return given().spec(requestSpecBuilder.build()).when().post("/1/lists");
    }
}
