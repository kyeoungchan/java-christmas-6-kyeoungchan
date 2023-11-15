package christmas.vo;

import christmas.consts.ConstantDate;
import christmas.exception.DayException;

import java.util.Objects;

public class Day {
    private final int date;

    public Day(int date) {
        validate(date);
        this.date = date;
    }

    private void validate(int date) {
        if (date < ConstantDate.FIRST_DATE.getDate() || date > ConstantDate.LAST_DATE.getDate()) {
            throw new DayException();
        }
    }

    public boolean isFriday() {
        return date % ConstantDate.WEEK_UNIT.getDate() == ConstantDate.FRIDAY_UNIT_DATE.getDate();
    }

    public boolean isSaturday() {
        return date % ConstantDate.WEEK_UNIT.getDate() == ConstantDate.SATURDAY_UNIT_DATE.getDate();
    }

    public boolean isSunday() {
        return date % ConstantDate.WEEK_UNIT.getDate() == ConstantDate.SUNDAY_UNIT_DATE.getDate();
    }

    public boolean isChristmasDDay() {
        return date == ConstantDate.CHRISTMAS_D_DAY.getDate();
    }

    public boolean isWeekday() {
        return !isWeekend();
    }

    public boolean isWeekend() {
        return isFriday() || isSaturday();
    }

    public int daysFromFirstDate() {
        return date - ConstantDate.CALCULATE_UNIT_FOR_CHRISTMAS_DAY_EVENT.getDate();
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
