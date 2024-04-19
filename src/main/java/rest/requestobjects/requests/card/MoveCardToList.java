package rest.requestobjects.requests.card;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import rest.requestobjects.requests.BaseRequest;

public class MoveCardToList extends BaseRequest {
    public MoveCardToList(String cardId, String idDestinationList, RequestSpecBuilder requestSpecBuilder) {
        this.requestSpecBuilder = requestSpecBuilder;
        this.requestSpecBuilder.addPathParam("cardId", cardId);
        this.requestSpecBuilder.addQueryParam("idList", idDestinationList);
    }

    @Override
    public Response execute() {
        return RestAssured.given().spec(requestSpecBuilder.build()).when().put("/1/cards/{cardId}");
    }
}
