package christmas.dto;

import christmas.consts.Badge;
import christmas.consts.Event;
import christmas.consts.Menu;
import christmas.vo.Money;

import java.util.EnumMap;

public record Result(EnumMap<Menu, Integer> orderMenus, Money totalPriceBeforeDiscount,
                     EnumMap<Menu, Integer> presentationCount, EnumMap<Event, Integer> benefitAmountsPerEvent,
                     Money totalBenefitPrice, Money totalPriceAfterDiscount, Badge badge) {
}
