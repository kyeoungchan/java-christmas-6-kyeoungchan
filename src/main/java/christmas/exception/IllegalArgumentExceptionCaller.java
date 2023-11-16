package christmas.exception;

import christmas.consts.ErrorMessage;

public class IllegalArgumentExceptionCaller {
    protected static void throwIllegalArgumentException(String message) {
        throw new IllegalArgumentException(generateMessage(message));
    }

    protected static void throwIllegalArgumentException(String message, Throwable e) {
        throw new IllegalArgumentException(generateMessage(message), e);
    }

    private static String generateMessage(String message) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(ErrorMessage.ERROR_PREFIX.getMessage());
        stringBuilder.append(message);
        stringBuilder.append(ErrorMessage.ERROR_POSTFIX.getMessage());
        return stringBuilder.toString();
    }
}
