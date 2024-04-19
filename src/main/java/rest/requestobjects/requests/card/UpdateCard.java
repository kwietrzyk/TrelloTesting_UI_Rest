package rest.requestobjects.requests.card;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import rest.requestobjects.requests.BaseRequest;

import java.util.Map;

public class UpdateCard extends BaseRequest {
    public UpdateCard(String cardId, Map<String, String> query, RequestSpecBuilder requestSpecBuilder) {
        this.requestSpecBuilder = requestSpecBuilder;
        this.requestSpecBuilder.addPathParam("cardId", cardId);
        for (Map.Entry<String, String> entry : query.entrySet()) {
            this.requestSpecBuilder.addQueryParam(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public Response execute() {
        return RestAssured.given().spec(requestSpecBuilder.build()).when().put("/1/cards/{cardId}");
    }
}
