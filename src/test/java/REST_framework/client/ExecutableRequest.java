package REST_framework.client;

import io.restassured.response.Response;

@FunctionalInterface
public interface ExecutableRequest {

    Response execute();
}
