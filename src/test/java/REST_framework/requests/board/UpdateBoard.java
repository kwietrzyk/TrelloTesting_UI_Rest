package REST_framework.requests.board;

import REST_framework.client.ExecutableRequest;
import REST_framework.requests.BaseRequest;
import dto.boardDto.main.BoardDto;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class UpdateBoard extends BaseRequest {

    public UpdateBoard(BoardDto body, RequestSpecBuilder requestSpecBuilder) {
        this.requestSpecBuilder = requestSpecBuilder;
        this.requestSpecBuilder.addPathParam("boardId", body.getId());
        this.requestSpecBuilder.setBody(body);
    }

    public UpdateBoard(String boardId, Map<String, String> query, RequestSpecBuilder requestSpecBuilder) {
        this.requestSpecBuilder = requestSpecBuilder;
        this.requestSpecBuilder.addPathParam("boardId", boardId);
        for (Map.Entry<String, String> entry : query.entrySet()) {
            this.requestSpecBuilder.addQueryParam(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public Response execute() {
        return given().spec(requestSpecBuilder.build()).when().put("/1/boards/{boardId}");
    }
}
