package configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestConfiguration {
    private static final String CONFIG_FILE_PATH = "src/main/resources/configuration.properties";
    private static final Properties PROPERTIES = new Properties();

    public static final String browser;
    public static final String baseUrl;
    public static final String apiKey;
    public static final String token;
    public static final String loginEmail;
    public static final String loginPassword;
    public static final String configUserName;

    public static final String MY_NEW_TABLE =  "MyNewTable";

    static {
        try {
            PROPERTIES.load(new FileInputStream(CONFIG_FILE_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
        browser = PROPERTIES.getProperty("browser").toLowerCase();
        baseUrl = PROPERTIES.getProperty("app.url");
        apiKey = PROPERTIES.getProperty("app.key");
        token = PROPERTIES.getProperty("app.token");
        loginEmail = PROPERTIES.getProperty("email");
        loginPassword = PROPERTIES.getProperty("password");
        configUserName = PROPERTIES.getProperty("userName").toLowerCase();
    }
}
