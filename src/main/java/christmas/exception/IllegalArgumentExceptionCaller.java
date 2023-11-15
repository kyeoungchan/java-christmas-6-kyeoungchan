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
        return ErrorMessage.ERROR_PREFIX.getMessage() + message + ErrorMessage.ERROR_POSTFIX.getMessage();
    }
}
