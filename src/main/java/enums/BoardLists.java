package enums;

public enum BoardLists {

    TODO("To do", "Do zrobienia"),      // default
    ONGOING("Ongoing", "W trakcie"),    // default
    DONE("Done", "Zrobione"),           // default
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
