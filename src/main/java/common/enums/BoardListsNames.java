package common.enums;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public enum BoardListsNames {

    TODO("To do", "Do zrobienia"),      // default
    ONGOING("Ongoing", "W trakcie"),    // default
    DONE("Done", "Zrobione"),           // default
    ONHOLD("On Hold", "Wstrzymane");

    private final String englishLabel;
    private final String polishLabel;

    BoardListsNames(String englishLabel, String polishLabel) {
        this.englishLabel = englishLabel;
        this.polishLabel = polishLabel;
    }

    public String getEnglishLabel() {
        return englishLabel;
    }

    public String getPolishLabel() {
        return polishLabel;
    }

    public static String getRandomDefaultName() {
        return getDefaultNames().get(new Random().nextInt(3)).polishLabel;
    }

    public static List<BoardListsNames> getDefaultNames() {
        return Arrays.asList(values()).subList(0, 3);
    }
}
