package christmas.domain;

import christmas.consts.ErrorMessage;
import christmas.consts.Menu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class OrderMenusTest {

    @Test
    @DisplayName("주문 개수 컬렉션이 빈 값이면 예외를 발생시킨다.")
    void validateEmpty() {
        EnumMap<Menu, Integer> menuCount = new EnumMap<>(Menu.class);
        assertThatThrownBy(() -> new OrderMenus(menuCount))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new OrderMenus(menuCount)).hasMessageContaining(
                ErrorMessage.ERROR_PREFIX.getMessage(),
                ErrorMessage.NOT_INPUTTED_MENU_AND_COUNT.getMessage(),
                ErrorMessage.ERROR_POSTFIX.getMessage());
    }

    @Test
    @DisplayName("0개의 개수를 입력하면 예외를 발생시킨다.")
    void getZeroCount() {
        EnumMap<Menu, Integer> menuCount = new EnumMap<>(Menu.class);
        menuCount.put(Menu.BARBCUE_RIBS, 0);
        assertThatThrownBy(() -> new OrderMenus(menuCount))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new OrderMenus(menuCount)).hasMessageContaining(
                        ErrorMessage.ERROR_PREFIX.getMessage(),
                        ErrorMessage.ORDER_ERROR.getMessage(),
                        ErrorMessage.ERROR_POSTFIX.getMessage());
    }

    @Test
    @DisplayName("음수의 개수를 입력하면 예외를 발생시킨다.")
    void getMinusCount() {
        EnumMap<Menu, Integer> menuCount = new EnumMap<>(Menu.class);
        menuCount.put(Menu.BARBCUE_RIBS, -1);
        assertThatThrownBy(() -> new OrderMenus(menuCount))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new OrderMenus(menuCount)).hasMessageContaining(
                ErrorMessage.ERROR_PREFIX.getMessage(),
                ErrorMessage.ORDER_ERROR.getMessage(),
                ErrorMessage.ERROR_POSTFIX.getMessage());
    }

    @Test
    @DisplayName("음료만 주문하면 예외를 발생시킨다.")
    void validateOnlyBeverage() {
        EnumMap<Menu, Integer> menuCount = new EnumMap<>(Menu.class);
        menuCount.put(Menu.COKE_ZERO, 1);
        assertThatThrownBy(() -> new OrderMenus(menuCount))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new OrderMenus(menuCount)).hasMessageContaining(
                ErrorMessage.ERROR_PREFIX.getMessage(),
                ErrorMessage.ORDERED_ONLY_BEVERAGE.getMessage(),
                ErrorMessage.ERROR_POSTFIX.getMessage());
    }

    @Test
    @DisplayName("메뉴 개수가 총 20개를 초과하면 예외를 발생시킨다.")
    void validateOverMaximumCount() {
        EnumMap<Menu, Integer> menuCount = new EnumMap<>(Menu.class);
        menuCount.put(Menu.T_BONE_STEAK, 20);
        assertThatThrownBy(() -> new OrderMenus(menuCount))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new OrderMenus(menuCount)).hasMessageContaining(
                ErrorMessage.ERROR_PREFIX.getMessage(),
                ErrorMessage.ORDERED_TOO_MUCH.getMessage(),
                ErrorMessage.ERROR_POSTFIX.getMessage());
    }

    @Test
    @DisplayName("메뉴 개수가 총 20개를 초과하면 예외를 발생시킨다.")
    void validateOverMaximumCountNotUnifiedMenu() {
        EnumMap<Menu, Integer> menuCount = new EnumMap<>(Menu.class);
        menuCount.put(Menu.T_BONE_STEAK, 10);
        menuCount.put(Menu.COKE_ZERO, 10);
        assertThatThrownBy(() -> new OrderMenus(menuCount))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new OrderMenus(menuCount)).hasMessageContaining(
                ErrorMessage.ERROR_PREFIX.getMessage(),
                ErrorMessage.ORDERED_TOO_MUCH.getMessage(),
                ErrorMessage.ERROR_POSTFIX.getMessage());
    }
}