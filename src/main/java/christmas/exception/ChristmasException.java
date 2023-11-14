package christmas.exception;

import christmas.consts.ErrorMessage;

public class ChristmasException extends IllegalArgumentException {
    public ChristmasException(String message) {
        super(generateErrorMessage(message));
    }

    public ChristmasException(String message, Throwable cause) {
        super(generateErrorMessage(message), cause);
    }

    private static String generateErrorMessage(String message) {
        return ErrorMessage.ERROR_PREFIX.getMessage() + message + ErrorMessage.ERROR_POSTFIX.getMessage();
    }
}
