package christmas.dto;

import christmas.vo.Money;

public record OrderBeforeEvents(Money totalOrderPrice, boolean canApplyToEvents) {
}
