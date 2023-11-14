package christmas.domain;

import christmas.consts.ErrorMessage;
import christmas.consts.Menu;
import christmas.consts.MenuKind;
import christmas.consts.SimpleConsts;
import christmas.exception.OrderException;

import java.util.EnumMap;

public class OrderMenus {
    private final EnumMap<Menu, Integer> menuCount;

    public OrderMenus(EnumMap<Menu, Integer> menuCount) {
        validate(menuCount);
        this.menuCount = menuCount;
    }

    private void validate(EnumMap<Menu, Integer> menuCount) {
        validateEmpty(menuCount);
        validateNaturalCount(menuCount);
        validateOrderOnlyBeverage(menuCount);
        validateOverMaximumCount(menuCount);
    }

    private void validateOverMaximumCount(EnumMap<Menu, Integer> menuCount) {
        int totalCount = menuCount.values().stream().mapToInt(count -> count).sum();
        if (totalCount >= SimpleConsts.MAXIMUM_COUNT.getValue()) {
            throw new OrderException(ErrorMessage.ORDERED_TOO_MUCH.getMessage());
        }
    }

    private void validateEmpty(EnumMap<Menu, Integer> menuCount) {
        if (menuCount.isEmpty()) {
            throw new OrderException(ErrorMessage.NOT_INPUTTED_MENU_AND_COUNT.getMessage());
        }
    }

    private void validateNaturalCount(EnumMap<Menu, Integer> menuCount) {
        boolean hasAnyNotNaturalCount = menuCount.values().stream()
                .anyMatch(count -> count <= SimpleConsts.NO_COUNT.getValue());
        if (hasAnyNotNaturalCount) {
            throw new OrderException();
        }
    }

    private void validateOrderOnlyBeverage(EnumMap<Menu, Integer> menuCount) {
        boolean orderOnlyBeverage = menuCount.keySet().stream().allMatch(MenuKind.BEVERAGE::isKindOf);
        if (orderOnlyBeverage) {
            throw new OrderException(ErrorMessage.ORDERED_ONLY_BEVERAGE.getMessage());
        }
    }

    public EnumMap<Menu, Integer> getMenuCount() {
        return menuCount;
    }
}
