package christmas.domain;

import christmas.consts.ConstantMoney;
import christmas.consts.MenuKind;
import christmas.vo.Day;
import christmas.vo.Money;
import christmas.vo.Order;

import java.util.Map;

public class WeekdayCalculator implements EventCalculatorAdapter {
    @Override
    public boolean supports(Day day, Money priceBeforeDiscount) {
        return day.isWeekday();
    }

    @Override
    public int discountPrice(Order order) {
        int dessertMenuCount = countDessertMenu(order);
        return -1 * dessertMenuCount * ConstantMoney.INCREASE_UNIT_FOR_WEEKDAY_EVENT.getAmount();
    }

    private int countDessertMenu(Order order) {
        return order.menuCount().entrySet()
                .stream()
                .filter(entry -> MenuKind.DESSERT.isKindOf(entry.getKey()))
                .mapToInt(Map.Entry::getValue)
                .sum();
    }
}
