package christmas.consts;

public enum FormatForOutputView {
    ORDER_MENU("%s %d개");

    private final String format;

    FormatForOutputView(String format) {
        this.format = format;
    }

    public String getFormatStringInt(String name, int value) {
        return String.format(this.format, name, value);
    }
}
