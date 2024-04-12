package tests.base;

// You can set browser via Configuration.browser = Browsers.<yourChoice>.getName()
public enum Browsers {
    CHROME("chrome"),
    FIREFOX("firefox"),
    EDGE("edge"),
    OPERA("opera"),
    SAFARI("safari");

    private final String browserName;

    Browsers(String browserName) {
        this.browserName = browserName;
    }

    public String getName() {
        return browserName;
    }
}
