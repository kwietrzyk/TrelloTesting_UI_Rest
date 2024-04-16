package tests.base;

import io.qameta.allure.Step;
import org.junit.jupiter.api.BeforeEach;
import rest.helpers.RestHelper;
import rest.helpers.RestInternalHelper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseTest {

    public static final Logger LOGGER = LoggerFactory.getLogger(BaseTest.class);
    public static final int DEFAULT_LISTS_AMOUNT = 3;
    public final RestHelper restHelper = new RestHelper();

    @Step("Preconditions")
    @BeforeEach
    public void clean() {
        LOGGER.info("Cleaning");
        RestInternalHelper cleaningHelper = new RestInternalHelper();
        cleaningHelper.deleteAllBoards();
    }
}