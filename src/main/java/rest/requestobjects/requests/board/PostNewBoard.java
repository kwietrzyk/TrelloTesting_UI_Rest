package rest.requestobjects.requests.board;

import io.restassured.RestAssured;
import rest.requestobjects.requests.BaseRequest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;

import java.util.Map;

public class PostNewBoard extends BaseRequest {

    // Create board without body, just name
    public PostNewBoard(String boardName, RequestSpecBuilder requestSpecBuilder) {
        this.requestSpecBuilder = requestSpecBuilder;;
        this.requestSpecBuilder.addQueryParam("name", boardName);
    }

    // Create board with query map
    public PostNewBoard(Map<String, String> query, RequestSpecBuilder requestSpecBuilder) {
        this.requestSpecBuilder = requestSpecBuilder;
        for (Map.Entry<String, String> entry : query.entrySet()) {
            this.requestSpecBuilder.addQueryParam(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public Response execute() {
        return RestAssured.given().spec(requestSpecBuilder.build()).when().post("/1/boards");
    }
}
