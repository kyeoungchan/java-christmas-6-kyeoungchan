package christmas.util;

import christmas.consts.SimpleConsts;
import christmas.exception.DayException;

public class InputValidator {
    public int parseToValidatedInt(String inputtedNumber) {
        validateEmpty(inputtedNumber);
        int validatedNumber = 0;
        validatedNumber = validateNumber(inputtedNumber);
        validateMemorySafe(validatedNumber);
        return validatedNumber;
    }

    private void validateEmpty(String inputtedNumber) {
        if (inputtedNumber == null || inputtedNumber.isEmpty()) {
            throw new DayException();
        }
    }

    private int validateNumber(String inputtedNumber) {
        int validatedNumber;
        try {
            validatedNumber = Integer.parseInt(inputtedNumber);
        } catch (NumberFormatException e) {
            throw new DayException(e);
        }
        return validatedNumber;
    }

    private void validateMemorySafe(int validatedNumber) {
        if (Math.abs(validatedNumber) > SimpleConsts.MEMORY_LIMIT.getValue()) {
            throw new DayException();
        }
    }
}
