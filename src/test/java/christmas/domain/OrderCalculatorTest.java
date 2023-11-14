package christmas.domain;

import christmas.consts.Menu;
import christmas.dto.OrderBeforeEvents;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;

import static org.assertj.core.api.Assertions.assertThat;

class OrderCalculatorTest {
    private final OrderCalculator orderCalculator = new OrderCalculator();
    private final EnumMap<Menu, Integer> menuCounts = new EnumMap<>(Menu.class);

    @Test
    @DisplayName("메뉴 가격들을 다 더한 가격이 10,000원 이상이면 이벤트에 참여 가능하다.")
    void calculateTotalPriceAndCanApplyToEvents() {
        Menu orderMenu = Menu.T_BONE_STEAK;
        int totalAmount = orderMenu.getAmount();
        menuCounts.put(orderMenu, 1);

        OrderBeforeEvents result = orderCalculator.calculateTotalPriceAndCanApplyToEvents(menuCounts);

        assertThat(result.totalOrderPrice().getAmount()).isEqualTo(totalAmount);
        assertThat(result.canApplyToEvents()).isTrue();
    }

    @Test
    @DisplayName("메뉴 가격들을 다 더한 가격이 10,000원 이하면 이벤트에 참여가 불가능하다.")
    void calculateTotalPriceAndCannotApplyToEvents() {
        Menu orderMenu = Menu.BUTTON_MUSHROOM_SOUP;
        int totalAmount = orderMenu.getAmount();
        menuCounts.put(orderMenu, 1);

        OrderBeforeEvents result = orderCalculator.calculateTotalPriceAndCanApplyToEvents(menuCounts);

        assertThat(result.totalOrderPrice().getAmount()).isEqualTo(totalAmount);
        assertThat(result.canApplyToEvents()).isFalse();
    }
}