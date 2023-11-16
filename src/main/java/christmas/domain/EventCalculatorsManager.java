package christmas.domain;

import christmas.consts.ConstantMoney;
import christmas.consts.Event;
import christmas.consts.Menu;
import christmas.dto.EventsResult;
import christmas.dto.OrderForEvents;
import christmas.vo.Day;
import christmas.vo.Money;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

public class EventCalculatorsManager {
    private final EnumMap<Event, Money> benefitAmounts;

    public EventCalculatorsManager() {
        benefitAmounts = new EnumMap<>(Event.class);
    }

    public EventsResult generateEventsResult(Day visitingDay, EnumMap<Menu, Integer> orderMenus,
                                             Money totalPriceBeforeDiscount) {
        generateBenefitAmounts(visitingDay, orderMenus, totalPriceBeforeDiscount);
        EnumMap<Menu, Integer> presentationCount = generatePresentCount();
        Money totalBenefitPrice = calculateTotalBenefitPrice();
        return new EventsResult(benefitAmounts, presentationCount, totalBenefitPrice);
    }

    private void generateBenefitAmounts(Day visitingDay, EnumMap<Menu, Integer> orderMenus,
                                        Money totalPriceBeforeDiscount) {
        List<Menu> menus = new ArrayList<>();
        orderMenus.forEach((key, value) -> menus.add(key));
        for (Event event : Event.values()) {
            EventCalculatorAdapter calculator = event.getEventCalculator();
            if (!calculator.supports(visitingDay, totalPriceBeforeDiscount, menus)) {
                continue;
            }
            OrderForEvents order = new OrderForEvents(visitingDay, orderMenus, totalPriceBeforeDiscount);
            Money discountPrice = calculator.discountPrice(order);
            benefitAmounts.put(event, discountPrice);
        }
    }

    private EnumMap<Menu, Integer> generatePresentCount() {
        EnumMap<Menu, Integer> presentationCount = new EnumMap<>(Menu.class);
        Money discountAmountByPresent = benefitAmounts.get(Event.PRESENTATION_EVENT);
        if (discountAmountByPresent == null) {
            return presentationCount;
        }
        int positiveAmount = discountAmountByPresent.amount() * ConstantMoney.SIGN_INVERTER.getAmount();
        presentationCount.put(Menu.CHAMPAGNE, positiveAmount / Menu.CHAMPAGNE.getAmount());
        return presentationCount;
    }

    private Money calculateTotalBenefitPrice() {
        return new Money(benefitAmounts
                .values().stream()
                .mapToInt(Money::amount).sum());
    }
}
