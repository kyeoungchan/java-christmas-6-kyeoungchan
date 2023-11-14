package christmas.domain;

import christmas.vo.Day;
import christmas.vo.Money;
import christmas.dto.OrderForEvents;

public interface EventCalculatorAdapter {
    boolean supports(Day day, Money priceBeforeDiscount);
    Money discountPrice(OrderForEvents orderForEvents);
}
