package christmas.domain;

import christmas.vo.Day;
import christmas.vo.Money;
import christmas.vo.Order;

public interface EventCalculatorAdapter {
    boolean supports(Day day, Money priceBeforeDiscount);
    Money discountPrice(Order order);
}
