package christmas.exception;

import christmas.consts.ErrorMessage;

public class OrderException extends ChristmasException {
    public OrderException() {
        super(ErrorMessage.UNVALIDATED_ORDER.getMessage());
    }

    public OrderException(String message) {
        super(message);
    }

    public OrderException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderException(Throwable cause) {
        super(ErrorMessage.UNVALIDATED_ORDER.getMessage(), cause);
    }
}
