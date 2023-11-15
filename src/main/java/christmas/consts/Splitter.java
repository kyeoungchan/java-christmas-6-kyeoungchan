package christmas.consts;

public enum Splitter {
    COMMA(","),
    BAR("-");

    private final String regex;

    Splitter(String regex) {
        this.regex = regex;
    }

    public String getRegex() {
        return regex;
    }
}
