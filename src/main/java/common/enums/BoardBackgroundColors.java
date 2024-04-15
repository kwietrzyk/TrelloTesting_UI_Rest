package common.enums;
import java.util.Random;

public enum BoardBackgroundColors {

    BLUE("blue"),
    ORANGE("orange"),
    GREEN("green"),
    RED("red"),
    PURPLE("purple"),
    PINK("pink"),
    LIME("lime"),
    SKY("sky"),
    GREY("grey");

    private final String label;
    private static final Random RANDOM = new Random();

    BoardBackgroundColors(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static BoardBackgroundColors getRandom() {
        return values()[RANDOM.nextInt(values().length)];
    }
}
