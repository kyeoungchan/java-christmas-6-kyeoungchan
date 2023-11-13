package christmas.domain;

import christmas.vo.Day;
import christmas.vo.Money;
import christmas.vo.Order;

public class ChristmasDayCalculator implements EventCalculatorAdapter {

    @Override
    public boolean supports(Day day, Money priceBeforeDiscount) {
        return day.isFriday() || day.isSaturday();
    }

    @Override
    public Money discountPrice(Order order) {
        return null;
    }
}
