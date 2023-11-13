package christmas.consts;

public enum ConstantMoney {
    INITIAL_DISCOUNT_AMOUNT_FOR_CHRISTMAS_DAY_EVENT(1_000),
    INCREASE_UNIT_FOR_CHRISTMAS_DAY_EVENT(100),
    INCREASE_UNIT_FOR_WEEKDAY_EVENT(2_023),
    SIGN_INVERTER(-1);

    private final int amount;

    ConstantMoney(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }
}
