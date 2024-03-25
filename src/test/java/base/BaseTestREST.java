package base;

import REST_framework.client.ApiClient;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;
import java.util.Map;

public class BaseTestREST extends BaseTest {

    protected ApiClient apiClient = createApiClient();

    @BeforeEach
    public void setup() {
        apiKey = PROPERTIES.getProperty("app.key");
        token = PROPERTIES.getProperty("app.token");
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

    protected void deleteAllMyNewTableBoards() {
        List<Map<String, Object>> boardsList = apiClient.getAllBoards(configUserName).execute().jsonPath().getList("");
        for (var board : boardsList) {
            String boardName = board.get("name").toString();
            if (boardName.equals(MY_NEW_TABLE)) {
                apiClient.deleteBoard(board.get("id").toString()).execute();
            }
        }
    }
}
