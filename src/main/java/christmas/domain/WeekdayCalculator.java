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
        int mainMenuCount = countMainMenu(order);
        return -1 * mainMenuCount * ConstantMoney.INCREASE_UNIT_FOR_WEEKDAY_EVENT.getAmount();
    }

    private int countMainMenu(Order order) {
        return order.menues().entrySet()
                .stream()
                .filter(entry -> MenuKind.DESSERT.isKindOf(entry.getKey()))
                .mapToInt(Map.Entry::getValue)
                .sum();
    }
}
