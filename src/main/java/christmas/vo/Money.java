package christmas.vo;

import java.util.Objects;

public record Money(int amount) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return amount() == money.amount();
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount());
    }
}
