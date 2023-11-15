package christmas.exception;

import christmas.consts.ErrorMessage;

public class OrderExceptionCaller extends IllegalArgumentExceptionCaller {
    public static void throwOrderException(String message) {
        throwIllegalArgumentException(message);
    }

    public static void throwOrderException() {
        throwIllegalArgumentException(ErrorMessage.UNVALIDATED_ORDER.getMessage());
    }
}
