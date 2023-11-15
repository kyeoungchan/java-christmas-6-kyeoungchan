package christmas.vo;

import java.util.Objects;

public class Money {
    private final int amount;

    public Money(int amount) {
        this.amount = amount;
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
