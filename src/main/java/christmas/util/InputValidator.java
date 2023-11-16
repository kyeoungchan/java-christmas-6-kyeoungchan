package christmas.util;

import christmas.consts.Menu;
import christmas.consts.SimpleConsts;
import christmas.consts.Splitter;
import christmas.domain.MenuOrderAssembler;
import christmas.exception.DayExceptionCaller;
import christmas.exception.OrderExceptionCaller;

import java.util.EnumMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class InputValidator {
    private final MenuOrderAssembler menuOrderAssembler;

    public InputValidator(MenuOrderAssembler menuOrderAssembler) {
        this.menuOrderAssembler = menuOrderAssembler;
    }

    public int parseToValidatedDate(String inputtedNumber) {
        int validatedNumber = 0;
        try {
            validateEmpty(inputtedNumber);
            validatedNumber = validateNumber(inputtedNumber);
        } catch (IllegalArgumentException e) {
            DayExceptionCaller.throwDayException(e);
        }
        validateMemorySafe(validatedNumber);
        return validatedNumber;
    }

    private void validateEmpty(String inputtedString) {
        if (inputtedString == null || inputtedString.isEmpty()) {
            throw new IllegalArgumentException();
        }
    }

    private int validateNumber(String inputtedNumber) {
        int validatedNumber = 0;
        try {
            validatedNumber = Integer.parseInt(inputtedNumber);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(e);
        }
        return validatedNumber;
    }

    private void validateMemorySafe(int validatedNumber) {
        if (Math.abs(validatedNumber) > SimpleConsts.MEMORY_LIMIT.getValue()) {
            DayExceptionCaller.throwDayException();
        }
    }

    public EnumMap<Menu, Integer> validateMenus(String inputtedDate) {
        List<String> splitMenuNames = new ArrayList<>();
        List<Integer> counts = null;
        try {
            List<String> splitByComma = splitByRegex(inputtedDate, Splitter.COMMA.getRegex());
            List<String> splitCounts = new ArrayList<>();
            splitToMenuAndCounts(splitByComma, splitMenuNames, splitCounts);
            counts = getValidatedCounts(splitCounts);
        } catch (IllegalArgumentException e) {
            OrderExceptionCaller.throwOrderException(e);
        }
        return menuOrderAssembler.generateMenuCounts(splitMenuNames, counts);
    }

    private List<String> splitByRegex(String data, String regex) {
        return Arrays.stream(data.split(regex))
                .map(String::trim)
                .peek(this::validateEmpty)
                .toList();
    }

    private void splitToMenuAndCounts(
            List<String> splitByComma, List<String> splitMenuNames, List<String> splitCounts) {
        int menuIndex = 0;
        int countIndex = 1;
        splitByComma.forEach(data -> {
            List<String> menuAndCounts = splitByRegex(
                    data,
                    Splitter.BAR.getRegex(),
                    SimpleConsts.SPLIT_NUMBER_BY_MENU_COUNT.getValue()
            );
            splitMenuNames.add(menuAndCounts.get(menuIndex));
            splitCounts.add(menuAndCounts.get(countIndex));
        });
    }

    private List<String> splitByRegex(String data, String regex, int fixedSize) {
        List<String> splitResult = splitByRegex(data, regex);
        if (splitResult.size() != fixedSize) {
            OrderExceptionCaller.throwOrderException();
        }
        return splitResult;
    }

    private List<Integer> getValidatedCounts(List<String> menuCountsData) {
        return menuCountsData.stream().map(this::validateNumber).toList();
    }
}
