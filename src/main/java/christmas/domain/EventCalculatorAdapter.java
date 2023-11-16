package christmas.domain;

import christmas.consts.Menu;
import christmas.vo.Day;
import christmas.vo.Money;
import christmas.dto.OrderForEvents;

import java.util.List;

public interface EventCalculatorAdapter {
    boolean supports(Day day, Money priceBeforeDiscount, List<Menu> menus);
    Money discountPrice(OrderForEvents orderForEvents);
}
