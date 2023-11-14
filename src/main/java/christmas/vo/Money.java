package christmas.vo;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return getAmount() == money.getAmount();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAmount());
    }
}
