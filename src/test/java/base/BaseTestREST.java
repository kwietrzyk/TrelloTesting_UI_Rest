package base;

import REST_framework.client.ApiClient;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;

import java.io.FileInputStream;
import java.io.IOException;

public class BaseTestREST extends BaseTest {

    @BeforeAll
    public static void baseSetup() throws IOException {
        properties.load(new FileInputStream(configFilePath));
        baseUrl = properties.getProperty("app.url");
        configUserName = properties.getProperty("userName");
        apiKey = properties.getProperty("app.key");
        token = properties.getProperty("app.token");
        loginEmail = properties.getProperty("email");
        loginPassword = properties.getProperty("password");

//        reqSpec = RestAssured.requestSpecification = new RequestSpecBuilder()
//
//                .build();
//
//        respSpec = RestAssured.responseSpecification = new ResponseSpecBuilder()
//                .expectContentType(ContentType.JSON)
//                .build();
    }

    protected ApiClient createApiClient() {
        return new ApiClient(() -> new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .setContentType(ContentType.JSON)
                .addQueryParam("key", apiKey)
                .addQueryParam("token", token)
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter()));
    }
}
