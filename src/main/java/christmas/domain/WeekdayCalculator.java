package christmas.domain;

import christmas.consts.ConstantMoney;
import christmas.consts.MenuKind;
import christmas.dto.OrderForEvents;
import christmas.vo.Day;
import christmas.vo.Money;

import java.util.Map;

public class WeekdayCalculator implements EventCalculatorAdapter {
    @Override
    public boolean supports(Day day, Money priceBeforeDiscount) {
        return day.isWeekday();
    }

    @Override
    public int discountPrice(OrderForEvents orderForEvents) {
        int dessertMenuCount = countDessertMenu(orderForEvents);
        return dessertMenuCount * ConstantMoney.INCREASE_UNIT_FOR_WEEKDAY_EVENT.getAmount()
                * ConstantMoney.SIGN_INVERTER.getAmount();
    }

    private int countDessertMenu(OrderForEvents orderForEvents) {
        return orderForEvents.menuCount().entrySet()
                .stream()
                .filter(entry -> MenuKind.DESSERT.isKindOf(entry.getKey()))
                .mapToInt(Map.Entry::getValue)
                .sum();
    }
}
