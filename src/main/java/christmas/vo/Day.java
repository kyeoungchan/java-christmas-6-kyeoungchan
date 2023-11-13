package christmas.vo;

import christmas.consts.ConstantDate;

import java.util.Objects;

public class Day {
    private final int date;

    public Day(int date) {
        validate(date);
        this.date = date;
    }

    private void validate(int date) {

    }

    public boolean isFriday() {
        return date % ConstantDate.WEEK_UNIT.getDate() == ConstantDate.FRIDAY_UNIT_DATE.getDate();
    }

    public boolean isSaturday() {
        return date % ConstantDate.FRIDAY_UNIT_DATE.getDate() == ConstantDate.SATURDAY_UNIT_DATE.getDate();
    }

    public boolean isWeekday() {
        return !isWeekend();
    }

    public boolean isWeekend() {
        return isFriday() || isSaturday();
    }

    public int getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Day day1 = (Day) o;
        return date == day1.date;
    }

    @Override
    public int hashCode() {
        return Objects.hash(date);
    }
}
