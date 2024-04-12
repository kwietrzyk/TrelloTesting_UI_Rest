package tests.base;

import REST_framework.client.ApiClient;
import configuration.TestConfiguration;
import helpers.RestHelper;
import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseTest {

    public static final ApiClient API_CLIENT = createApiClient();
    public static final Logger LOGGER = LoggerFactory.getLogger(BaseTest.class);
    public static final int DEFAULT_LISTS_AMOUNT = 3;

    @Step("Creating API Client")
    protected static ApiClient createApiClient() {
        return new ApiClient(() -> new RequestSpecBuilder()
                .setBaseUri(TestConfiguration.baseUrl)
                .setContentType(ContentType.JSON)
                .addQueryParam("key", TestConfiguration.apiKey)
                .addQueryParam("token", TestConfiguration.token)
                .log(LogDetail.METHOD));
//                .addFilter(new RequestLoggingFilter())    // uncomment for extend logs
//                .addFilter(new ResponseLoggingFilter()));     // uncomment for extend logs
    }

    @AfterEach
    public void clean() {
        LOGGER.info("Teardown cleaning");
        RestHelper.deleteAllBoards();
    }
}