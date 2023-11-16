package christmas.domain;

import christmas.consts.ConstantMoney;
import christmas.consts.Menu;
import christmas.consts.MenuKind;
import christmas.dto.OrderForEvents;
import christmas.vo.Day;
import christmas.vo.Money;

import java.util.List;
import java.util.Map;

public class WeekdayCalculator implements EventCalculatorAdapter {
    @Override
    public boolean supports(Day day, Money priceBeforeDiscount, List<Menu> menus) {
        boolean isThereDessertMenu = menus.stream().anyMatch(MenuKind.DESSERT::isKindOf);
        return day.isWeekday() && isThereDessertMenu;
    }

    @Override
    public Money discountPrice(OrderForEvents orderForEvents) {
        int dessertMenuCount = countDessertMenu(orderForEvents);
        return new Money(dessertMenuCount
                * ConstantMoney.INCREASE_UNIT_FOR_WEEKDAY_EVENT.getAmount()
                * ConstantMoney.SIGN_INVERTER.getAmount());
    }

    private int countDessertMenu(OrderForEvents orderForEvents) {
        return orderForEvents.menuCount().entrySet()
                .stream()
                .filter(entry -> MenuKind.DESSERT.isKindOf(entry.getKey()))
                .mapToInt(Map.Entry::getValue)
                .sum();
    }
}
