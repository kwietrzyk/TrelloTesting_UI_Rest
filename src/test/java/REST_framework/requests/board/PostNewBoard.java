package REST_framework.requests.board;

import REST_framework.requests.BaseRequest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

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
        this.requestSpecBuilder.log(LogDetail.ALL);
    }

    @Override
    public Response execute() {
        return given().spec(requestSpecBuilder.build()).when().post("/1/boards");
    }
}
