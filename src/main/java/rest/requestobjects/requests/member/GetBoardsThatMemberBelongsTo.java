package rest.requestobjects.requests.member;

import io.restassured.RestAssured;
import rest.requestobjects.requests.BaseRequest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;

public class GetBoardsThatMemberBelongsTo extends BaseRequest {
    public GetBoardsThatMemberBelongsTo(String memberUsername, RequestSpecBuilder requestSpecBuilder) {
        this.requestSpecBuilder = requestSpecBuilder;
        this.requestSpecBuilder.addPathParam("userId", memberUsername);
    }

    @Override
    public Response execute() {
        return RestAssured.given().spec(requestSpecBuilder.build()).when().get("/1/members/{userId}/boards");
    }
}
