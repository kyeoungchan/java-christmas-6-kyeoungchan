package christmas.dto;

import christmas.consts.Menu;
import christmas.vo.Money;

import java.util.EnumMap;

public record CalculateResult(EnumMap<Menu, Integer> orderMenus, Money totalPriceBeforeDiscount,
                              EventsResult eventsResult, Money totalPriceAfterDiscount) {
}
