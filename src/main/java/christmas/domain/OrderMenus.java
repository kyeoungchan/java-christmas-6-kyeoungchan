package christmas.domain;

import christmas.consts.Menu;

import java.util.EnumMap;

public class OrderMenus {
    private final EnumMap<Menu, Integer> menuCount;

    public OrderMenus(EnumMap<Menu, Integer> menuCount) {
        validate(menuCount);
        this.menuCount = menuCount;
    }

    private void validate(EnumMap<Menu, Integer> menuCount) {

    }

    public EnumMap<Menu, Integer> getMenuCount() {
        return menuCount;
    }
}
