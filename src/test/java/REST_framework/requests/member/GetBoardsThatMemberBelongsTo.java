package REST_framework.requests.member;

import REST_framework.client.ExecutableRequest;
import REST_framework.requests.BaseRequest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GetBoardsThatMemberBelongsTo extends BaseRequest {
    public GetBoardsThatMemberBelongsTo(String memberUsername, RequestSpecBuilder requestSpecBuilder) {
        this.requestSpecBuilder = requestSpecBuilder;
        this.requestSpecBuilder.addPathParam("userId", memberUsername);
    }

    @Override
    public Response execute() {
        return given().spec(requestSpecBuilder.build()).when().get("/1/members/{userId}/boards");
    }
}
