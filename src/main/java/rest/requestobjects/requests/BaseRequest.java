package rest.requestobjects.requests;

import io.restassured.response.Response;
import io.restassured.builder.RequestSpecBuilder;

public abstract class BaseRequest {

    protected RequestSpecBuilder requestSpecBuilder;
    public abstract Response execute();
}
