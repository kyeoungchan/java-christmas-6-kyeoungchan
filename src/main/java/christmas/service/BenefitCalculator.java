package christmas.service;

import christmas.consts.ConstantMoney;
import christmas.consts.Event;
import christmas.consts.Menu;
import christmas.domain.EventCalculatorsManager;
import christmas.domain.OrderCalculator;
import christmas.domain.OrderMenus;
import christmas.dto.CalculateResult;
import christmas.dto.EventsResult;
import christmas.dto.OrderBeforeEvents;
import christmas.vo.Day;
import christmas.vo.Money;

import java.util.EnumMap;

public class BenefitCalculator {
    private final OrderCalculator orderCalculator;
    private final EventCalculatorsManager eventCalculatorsManager;

    public BenefitCalculator() {
        orderCalculator = new OrderCalculator();
        eventCalculatorsManager = new EventCalculatorsManager();
    }

    public CalculateResult generateBenefitDetails(Day visitingDay, OrderMenus orderMenus) {
        EnumMap<Menu, Integer> menuCount = orderMenus.getMenuCount();
        OrderBeforeEvents orderBeforeEvents = beforeEvents(menuCount);
        Money totalPriceBeforeDiscount = orderBeforeEvents.totalOrderPrice();
        if (!canApply(orderBeforeEvents)) {
            return generateNoEventDetail(menuCount, totalPriceBeforeDiscount);
        }
        EventsResult eventsResult = getEventsResult(visitingDay, menuCount, totalPriceBeforeDiscount);
        Money totalBenefitPrice = eventsResult.totalBenefitPrice();
        Money totalPriceAfterDiscount =
                calculateTotalPriceAfterDiscount(totalPriceBeforeDiscount, totalBenefitPrice, eventsResult.presentationCount());
        return new CalculateResult(menuCount, totalPriceBeforeDiscount,
                eventsResult, totalPriceAfterDiscount);
    }

    private OrderBeforeEvents beforeEvents(EnumMap<Menu, Integer> orderMenus) {
        return orderCalculator.calculateTotalPriceIfCanApplyToEvents(orderMenus);
    }

    private boolean canApply(OrderBeforeEvents orderBeforeEvents) {
        return orderBeforeEvents.canApplyToEvents();
    }

    private CalculateResult generateNoEventDetail(EnumMap<Menu, Integer> orderMenus, Money totalPriceBeforeDiscount) {
        EventsResult eventsResult = getNoneEventResult();
        return new CalculateResult(orderMenus, totalPriceBeforeDiscount, eventsResult, totalPriceBeforeDiscount);
    }

    private EventsResult getNoneEventResult() {
        EnumMap<Event, Money> benefitAmounts = new EnumMap<>(Event.class);
        EnumMap<Menu, Integer> presentationCount = new EnumMap<>(Menu.class);
        Money notPaidPrice = new Money(ConstantMoney.NOT_PAID.getAmount());
        return new EventsResult(benefitAmounts, presentationCount, notPaidPrice);
    }

    private EventsResult getEventsResult(Day visitingDay, EnumMap<Menu, Integer> orderMenus, Money totalPriceBeforeDiscount) {
        return eventCalculatorsManager
                .generateEventsResult(visitingDay, orderMenus, totalPriceBeforeDiscount);
    }

    private Money calculateTotalPriceAfterDiscount(Money totalPriceBeforeDiscount,
                                                   Money totalBenefitPrice,
                                                   EnumMap<Menu, Integer> presentationCount) {
        int totalPresentAmounts = presentationCount.entrySet()
                .stream()
                .mapToInt(e -> e.getKey().getAmount() * e.getValue())
                .sum();
        return new Money(totalPriceBeforeDiscount.amount() + totalBenefitPrice.amount() - totalPresentAmounts);
    }
}
