package base;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseTest {
    protected static final String CONFIG_FILE_PATH = "src/test/resources/configuration.properties";
    protected static final Properties PROPERTIES = new Properties();
    protected static final Faker FAKER = new Faker();
    protected static final String MY_NEW_TABLE =  "MyNewTable";
    protected static String baseUrl;
    protected static String configUserName;
    protected static String apiKey;
    protected static String token;
    protected static String loginEmail;
    protected static String loginPassword;

    @BeforeAll
    protected static void baseSetup() throws IOException {
        PROPERTIES.load(new FileInputStream(CONFIG_FILE_PATH));
        baseUrl = PROPERTIES.getProperty("app.url");
        configUserName = PROPERTIES.getProperty("userName");
    }
}