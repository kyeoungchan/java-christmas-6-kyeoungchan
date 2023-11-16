package christmas.domain;

import christmas.consts.ErrorMessage;
import christmas.consts.Menu;
import christmas.exception.OrderExceptionCaller;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.List;

public class MenuOrderAssembler {
    public EnumMap<Menu, Integer> generateMenuCounts(List<String> menuNamesData, List<Integer> counts) {
        List<Menu> validatedMenuNames = getValidatedMenuNames(menuNamesData);
        validateInternalError(counts, validatedMenuNames);
        return assembleOrderMenus(validatedMenuNames, counts);
    }

    private void validateInternalError(List<Integer> counts, List<Menu> validatedMenuNames) {
        if (validatedMenuNames.size() != counts.size()) {
            OrderExceptionCaller.throwOrderException(ErrorMessage.INTERNAL.getMessage());
        }
    }

    private EnumMap<Menu, Integer> assembleOrderMenus(
            List<Menu> validatedMenuNames, List<Integer> counts) {
        EnumMap<Menu, Integer> orderMenus = new EnumMap<>(Menu.class);
        for (int i = 0; i < validatedMenuNames.size(); i++) {
            orderMenus.put(validatedMenuNames.get(i), counts.get(i));
        }
        return orderMenus;
    }

    private List<Menu> getValidatedMenuNames(List<String> menuNamesData) {
        List<Menu> menuNames = getMenus(menuNamesData);
        validateDuplicatedOrder(menuNames);
        return menuNames;
    }

    private List<Menu> getMenus(List<String> menuNamesData) {
        return menuNamesData.stream()
                .map(this::parseToMenu)
                .toList();
    }

    private Menu parseToMenu(String menuNameDate) {
        return Arrays.stream(Menu.values())
                .filter(menu -> menu.isThisMenu(menuNameDate))
                .findAny()
                .orElseGet(() -> {
                    OrderExceptionCaller.throwOrderException();
                    return null;
                });
    }

    private void validateDuplicatedOrder(List<Menu> menuNames) {
        if (menuNames.size() != new HashSet<>(menuNames).size()) {
            OrderExceptionCaller.throwOrderException();
        }
    }
}
