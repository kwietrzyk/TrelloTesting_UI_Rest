package rest.requestobjects.requests.card;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import rest.requestobjects.requests.BaseRequest;

public class GetCard extends BaseRequest {
    public GetCard(String cardId, RequestSpecBuilder requestSpecBuilder) {
        this.requestSpecBuilder = requestSpecBuilder;
        this.requestSpecBuilder.addPathParam("cardId", cardId);
    }

    @Override
    public Response execute() {
        return RestAssured.given().spec(requestSpecBuilder.build()).when().get("/1/cards/{cardId}");
    }
}
