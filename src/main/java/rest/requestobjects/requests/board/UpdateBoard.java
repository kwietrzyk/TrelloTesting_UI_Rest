package rest.requestobjects.requests.board;

import io.restassured.RestAssured;
import rest.requestobjects.requests.BaseRequest;
import gui.dto.boardDto.main.BoardDto;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;

import java.util.Map;

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
        return RestAssured.given().spec(requestSpecBuilder.build()).when().put("/1/boards/{boardId}");
    }
}
