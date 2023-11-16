package christmas.consts;

public enum FormatForOutputView {
    MENU_COUNT("%s %,d개"),
    AMOUNT("%,d원"),
    EVENT_BENEFIT_AMOUNT("%s: %,d원");

    private final String format;

    FormatForOutputView(String format) {
        this.format = format;
    }

    public String getFormatStringInt(String name, int value) {
        return String.format(this.format, name, value);
    }

    public String getFormatMoney(int amount) {
        return String.format(format, amount);
    }
}
