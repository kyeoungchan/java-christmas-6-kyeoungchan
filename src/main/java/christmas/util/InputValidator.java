package christmas.util;

import christmas.consts.SimpleConsts;
import christmas.exception.DayExceptionCaller;

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
            DayExceptionCaller.throwDayException();
        }
    }

    private int validateNumber(String inputtedNumber) {
        int validatedNumber = 0;
        try {
            validatedNumber = Integer.parseInt(inputtedNumber);
        } catch (NumberFormatException e) {
            DayExceptionCaller.throwDayException(e);
        }
        return validatedNumber;
    }

    private void validateMemorySafe(int validatedNumber) {
        if (Math.abs(validatedNumber) > SimpleConsts.MEMORY_LIMIT.getValue()) {
            DayExceptionCaller.throwDayException();
        }
    }
}
