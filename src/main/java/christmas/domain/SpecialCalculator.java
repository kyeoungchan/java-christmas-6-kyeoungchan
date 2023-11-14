package christmas.domain;

import christmas.vo.Day;
import christmas.vo.Money;
import christmas.vo.Order;

public class SpecialCalculator implements EventCalculatorAdapter {
    @Override
    public boolean supports(Day day, Money priceBeforeDiscount) {
        return day.isSunday() || day.isChristmasDDay();
    }

    @Override
    public int discountPrice(Order order) {
        return 0;
    }
}
