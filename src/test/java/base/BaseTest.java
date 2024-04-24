package base;

import io.qameta.allure.Step;
import org.junit.jupiter.api.BeforeEach;
import rest.helpers.RestHelper;
import rest.helpers.RestInternalHelper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseTest {

    public static final Logger LOGGER = LoggerFactory.getLogger(BaseTest.class);
    public static final int DEFAULT_LISTS_AMOUNT = 3;
    protected final RestHelper restHelper = new RestHelper();
    private final RestInternalHelper cleaningHelper = new RestInternalHelper();

    @Step("Workspace cleaning")
    @BeforeEach
    public void clean() {
        LOGGER.info("Start of cleaning");
        cleaningHelper.deleteAllBoards();
        LOGGER.info("End of cleaning");
    }
}