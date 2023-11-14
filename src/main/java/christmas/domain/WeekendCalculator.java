package christmas.domain;

import christmas.consts.ConstantMoney;
import christmas.consts.MenuKind;
import christmas.dto.OrderForEvents;
import christmas.vo.Day;
import christmas.vo.Money;

import java.util.Map;

public class WeekendCalculator implements EventCalculatorAdapter {
    @Override
    public boolean supports(Day day, Money priceBeforeDiscount) {
        return day.isWeekend();
    }

    @Override
    public Money discountPrice(OrderForEvents orderForEvents) {
        int mainMenuCount = countMainMenu(orderForEvents);
        return new Money(mainMenuCount
                * ConstantMoney.INCREASE_UNIT_FOR_WEEKEND_EVENT.getAmount()
                * ConstantMoney.SIGN_INVERTER.getAmount());
    }

    private int countMainMenu(OrderForEvents orderForEvents) {
        return orderForEvents.menuCount().entrySet()
                .stream()
                .filter(entry -> MenuKind.MAIN.isKindOf(entry.getKey()))
                .mapToInt(Map.Entry::getValue)
                .sum();
    }
}
