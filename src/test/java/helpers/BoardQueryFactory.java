package helpers;

import com.github.javafaker.Faker;
import enums.BoardBackgroundColors;
import enums.BoardQuery;
import org.junit.jupiter.api.BeforeEach;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class BoardQueryFactory {

    private static final Faker FAKER = new Faker();
    private static Map<String, String> boardQueryParams = new TreeMap<>();

    public static Map<String, String> createPutQueryMap() {
        setBasicFields();
        boardQueryParams.put(BoardQuery.LABEL_NAMES_GREEN.getParam(), FAKER.superhero().name());
        boardQueryParams.put(BoardQuery.LABEL_NAMES_YELLOW.getParam(), FAKER.superhero().name());
        boardQueryParams.put(BoardQuery.LABEL_NAMES_ORANGE.getParam(), FAKER.superhero().name());
        boardQueryParams.put(BoardQuery.LABEL_NAMES_RED.getParam(), FAKER.superhero().name());
        boardQueryParams.put(BoardQuery.LABEL_NAMES_PURPLE.getParam(), FAKER.superhero().name());
        boardQueryParams.put(BoardQuery.LABEL_NAMES_BLUE.getParam(), FAKER.superhero().name());
        return boardQueryParams;
    }

    public static Map<String, String> createPostQueryMap() {
        setBasicFields();
        boardQueryParams = boardQueryParams.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey().replace("/", "_"),
                        Map.Entry::getValue));
        return boardQueryParams;
    }

    private static void setBasicFields() {
        boardQueryParams.clear();
        boardQueryParams.put(BoardQuery.NAME.getParam(), FAKER.artist().name());
        boardQueryParams.put(BoardQuery.DESC.getParam(), FAKER.weather().description());
        boardQueryParams.put(BoardQuery.PREFS_BACKGROUND.getParam(), BoardBackgroundColors.getRandom().getLabel());
    }
}