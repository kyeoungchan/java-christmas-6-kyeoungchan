package christmas.vo;

public class Money {
    private final int amount;

    public Money(int amount) {
        validate(amount);
        this.amount = amount;
    }

    private void validate(int amount) {

    }

    public int getAmount() {
        return amount;
    }
}
