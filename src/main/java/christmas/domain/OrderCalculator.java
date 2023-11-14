package christmas.domain;

import christmas.consts.ConstantMoney;
import christmas.consts.Menu;
import christmas.dto.OrderBeforeEvents;
import christmas.vo.Money;

import java.util.EnumMap;

public class OrderCalculator {
    // 이벤트 기간이 끝난 후 사용하기 위한 메서드
    public Money calculateTotalPrice(EnumMap<Menu, Integer> menuCounts) {
        int totalAmount = generateTotalAmount(menuCounts);
        return new Money(totalAmount);
    }

    public OrderBeforeEvents calculateTotalPriceAndCanApplyToEvents(EnumMap<Menu, Integer> menuCounts) {
        int totalAmount = generateTotalAmount(menuCounts);
        Money totalOrderPrice = new Money(totalAmount);
        boolean canApplyToEvents = canApplyToEvents(totalAmount);
        return new OrderBeforeEvents(totalOrderPrice, canApplyToEvents);
    }

    private int generateTotalAmount(EnumMap<Menu, Integer> menuCounts) {
        return menuCounts.entrySet().stream()
                .mapToInt(e -> e.getKey().getAmount() * e.getValue())
                .sum();
    }

    private boolean canApplyToEvents(int totalAmount) {
        return totalAmount >= ConstantMoney.STANDARD_FOR_EVENTS.getAmount();
    }
}
