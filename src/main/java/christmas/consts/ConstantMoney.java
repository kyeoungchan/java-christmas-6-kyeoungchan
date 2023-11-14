package christmas.consts;

public enum ConstantMoney {
    INITIAL_DISCOUNT_AMOUNT_FOR_CHRISTMAS_DAY_EVENT(1_000),
    INCREASE_UNIT_FOR_CHRISTMAS_DAY_EVENT(100),
    INCREASE_UNIT_FOR_WEEKDAY_EVENT(2_023),
    INCREASE_UNIT_FOR_WEEKEND_EVENT(2_023),
    SINGLE_UNIT_FOR_SPECIAL_EVENT(1_000),
    STANDARD_FOR_PRESENTATION_EVENT(120_000),
    SIGN_INVERTER(-1);

    private final int amount;

    ConstantMoney(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }
}
