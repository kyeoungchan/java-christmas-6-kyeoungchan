package christmas.exception;

import christmas.consts.ErrorMessage;

public class DayException extends ChristmasException {
    public DayException() {
        super(ErrorMessage.UNVALIDATED_DATE.getMessage());
    }

    public DayException(Throwable cause) {
        super(ErrorMessage.UNVALIDATED_DATE.getMessage(), cause);
    }
}
