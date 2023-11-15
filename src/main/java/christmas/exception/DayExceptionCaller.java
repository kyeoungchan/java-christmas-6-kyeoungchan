package christmas.exception;

import christmas.consts.ErrorMessage;

public class DayExceptionCaller extends IllegalArgumentExceptionCaller {
    public static void throwDayException(String message) {
        throwIllegalArgumentException(message);
    }

    public static void throwDayException() {
        throwIllegalArgumentException(ErrorMessage.UNVALIDATED_DATE.getMessage());
    }

    public static void throwDayException(String message, Throwable e) {
        throwIllegalArgumentException(message, e);
    }

    public static void throwDayException(Throwable e) {
        throwIllegalArgumentException(ErrorMessage.UNVALIDATED_DATE.getMessage(), e);
    }
}
