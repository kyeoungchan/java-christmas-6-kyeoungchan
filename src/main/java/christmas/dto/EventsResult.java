package christmas.dto;

import christmas.consts.Event;
import christmas.consts.Menu;
import christmas.vo.Money;

import java.util.EnumMap;

public record EventsResult(EnumMap<Event, Money> benefitAmounts, EnumMap<Menu, Integer> presentationCount, Money totalBenefitPrice) {
}
