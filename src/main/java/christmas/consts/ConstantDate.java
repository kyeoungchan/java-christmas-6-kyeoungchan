package christmas.consts;

public enum ConstantDate {
    FRIDAY_UNIT_DATE(1),
    SATURDAY_UNIT_DATE(2),
    WEEK_UNIT(7),
    LAST_DATE(31),
    CALCULATE_UNIT_FOR_CHRISTMAS_DAY_EVENT(1);

    private final int date;

    ConstantDate(int date) {
        this.date = date;
    }

    public int getDate() {
        return date;
    }
}