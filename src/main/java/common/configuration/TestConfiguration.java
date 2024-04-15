package common.configuration;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import rest.requestobjects.client.ApiClient;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestConfiguration {
    private static final String CONFIG_FILE_PATH = "src/main/resources/configuration.properties";
    private static final Properties PROPERTIES = new Properties();

    public static final String BROWSER;
    public static final String BASE_URL;
    public static final String API_KEY;
    public static final String TOKEN;
    public static final String LOGIN_EMAIL;
    public static final String LOGIN_PASSWORD;
    public static final String CONFIG_USER_NAME;
    public static final ApiClient API_CLIENT = createApiClient();

    static {
        try {
            PROPERTIES.load(new FileInputStream(CONFIG_FILE_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
        BROWSER = PROPERTIES.getProperty("browser").toLowerCase();
        BASE_URL = PROPERTIES.getProperty("app.url");
        API_KEY = PROPERTIES.getProperty("app.key");
        TOKEN = PROPERTIES.getProperty("app.token");
        LOGIN_EMAIL = PROPERTIES.getProperty("email");
        LOGIN_PASSWORD = PROPERTIES.getProperty("password");
        CONFIG_USER_NAME = PROPERTIES.getProperty("userName").toLowerCase();
    }

    protected static ApiClient createApiClient() {
        return new ApiClient(() -> new RequestSpecBuilder()
                .setBaseUri(TestConfiguration.BASE_URL)
                .setContentType(ContentType.JSON)
                .addQueryParam("key", TestConfiguration.API_KEY)
                .addQueryParam("token", TestConfiguration.TOKEN)
                .log(LogDetail.METHOD));
//                .addFilter(new RequestLoggingFilter())    // uncomment for extend logs
//                .addFilter(new ResponseLoggingFilter()));     // uncomment for extend logs
    }
}
