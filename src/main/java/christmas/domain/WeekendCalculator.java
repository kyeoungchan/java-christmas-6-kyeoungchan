package christmas.domain;

import christmas.consts.ConstantMoney;
import christmas.consts.MenuKind;
import christmas.vo.Day;
import christmas.vo.Money;
import christmas.vo.Order;

import java.util.Map;

public class WeekendCalculator implements EventCalculatorAdapter {
    @Override
    public boolean supports(Day day, Money priceBeforeDiscount) {
        return day.isWeekend();
    }

    @Override
    public int discountPrice(Order order) {
        int mainMenuCount = countMainMenu(order);
        return mainMenuCount * ConstantMoney.INCREASE_UNIT_FOR_WEEKEND_EVENT.getAmount()
                * ConstantMoney.SIGN_INVERTER.getAmount();
    }

    private int countMainMenu(Order order) {
        return order.menuCount().entrySet()
                .stream()
                .filter(entry -> MenuKind.MAIN.isKindOf(entry.getKey()))
                .mapToInt(Map.Entry::getValue)
                .sum();
    }
}
