package christmas.consts;

public enum Splitter {
    COMMA(","),
    BAR("-"),
    LINE_SEPARATOR("\n");

    private final String regex;

    Splitter(String regex) {
        this.regex = regex;
    }

    public String getRegex() {
        return regex;
    }
}
