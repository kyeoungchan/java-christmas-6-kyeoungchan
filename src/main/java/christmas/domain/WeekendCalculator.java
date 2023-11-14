package christmas.domain;

import christmas.vo.Day;
import christmas.vo.Money;
import christmas.vo.Order;

public class WeekendCalculator implements EventCalculatorAdapter {
    @Override
    public boolean supports(Day day, Money priceBeforeDiscount) {
        return day.isWeekend();
    }

    @Override
    public int discountPrice(Order order) {
        return 0;
    }
}
