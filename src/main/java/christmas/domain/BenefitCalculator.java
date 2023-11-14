package christmas.domain;

import christmas.consts.ConstantMoney;
import christmas.consts.Event;
import christmas.consts.Menu;
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

    public CalculateResult generateBenefitDetails(Day visitingDay, EnumMap<Menu, Integer> orderMenus) {
        OrderBeforeEvents orderBeforeEvents = beforeEvents(orderMenus);
        Money totalPriceBeforeDiscount = orderBeforeEvents.totalOrderPrice();
        if (!canApply(orderBeforeEvents)) {
            return generateNoEventDetail(orderMenus, totalPriceBeforeDiscount);
        }
        EventsResult eventsResult = getEventsResult(visitingDay, orderMenus, totalPriceBeforeDiscount);
        Money totalBenefitPrice = eventsResult.totalBenefitPrice();
        Money totalPriceAfterDiscount =
                calculateTotalPriceAfterDiscount(totalPriceBeforeDiscount, totalBenefitPrice);
        return new CalculateResult(orderMenus, totalPriceBeforeDiscount,
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
                                                   Money totalBenefitPrice) {
        return new Money(totalPriceBeforeDiscount.getAmount() + totalBenefitPrice.getAmount());
    }
}
