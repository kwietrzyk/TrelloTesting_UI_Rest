package enums;

public enum BoardLists {

    TODO("To do", "Do zrobienia"),
    ONGOING("Ongoing", "W trakcie"),
    DONE("Done", "Zrobione"),
    ONHOLD("On Hold", "Wstrzymane");

    private final String englishLabel;
    private final String polishLabel;

    BoardLists(String englishLabel, String polishLabel) {
        this.englishLabel = englishLabel;
        this.polishLabel = polishLabel;
    }

    public String getEnglishLabel() {
        return englishLabel;
    }

    public String getPolishLabel() {
        return polishLabel;
    }
}
