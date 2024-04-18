package rest.requestobjects.requests.card;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import rest.requestobjects.requests.BaseRequest;

public class PostNewCardToList extends BaseRequest {
    public PostNewCardToList(String cardName, String idList, RequestSpecBuilder requestSpecBuilder) {
        this.requestSpecBuilder = requestSpecBuilder;
        this.requestSpecBuilder.addQueryParam("name", cardName);
        this.requestSpecBuilder.addQueryParam("idList", idList);
    }

    @Override
    public Response execute() {
        return RestAssured.given().spec(requestSpecBuilder.build()).when().post("/1/cards");
    }
}
