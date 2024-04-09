package REST_framework.requests;

import REST_framework.client.ExecutableRequest;
import io.restassured.builder.RequestSpecBuilder;

public abstract class BaseRequest implements ExecutableRequest {
    protected RequestSpecBuilder requestSpecBuilder;
}
