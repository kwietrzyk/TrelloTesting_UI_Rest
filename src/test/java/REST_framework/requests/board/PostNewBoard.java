package REST_framework.requests.board;

import REST_framework.requests.BaseRequest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PostNewBoard extends BaseRequest {

    // Create board without body, just name
    public PostNewBoard(String boardName, RequestSpecBuilder requestSpecBuilder) {
        this.requestSpecBuilder = requestSpecBuilder;;
        this.requestSpecBuilder.addQueryParam("name", boardName);
    }

    // Create board with body
    public PostNewBoard(String boardName, Object body, RequestSpecBuilder requestSpecBuilder) {
        this(boardName, requestSpecBuilder);
        this.requestSpecBuilder.setBody(body);
    }

    @Override
    public Response execute() {
        return given().spec(requestSpecBuilder.build()).when().post("/1/boards");
    }
}
