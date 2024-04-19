package rest.requestobjects.requests.card;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import rest.requestobjects.requests.BaseRequest;

import java.util.Map;

public class AddCommentToCard extends BaseRequest {
    public AddCommentToCard(String cardId, String comment, RequestSpecBuilder requestSpecBuilder) {
        this.requestSpecBuilder = requestSpecBuilder;
        this.requestSpecBuilder.addPathParam("cardId", cardId);
        this.requestSpecBuilder.addQueryParam("text", comment);
    }

    @Override
    public Response execute() {
        return RestAssured.given().spec(requestSpecBuilder.build()).when().post("/1/cards/{cardId}/actions/comments");
    }
}
