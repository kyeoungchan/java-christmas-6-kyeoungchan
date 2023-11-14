package christmas.exception;

import christmas.consts.ErrorMessage;

public class OrderException extends ChristmasException {
    public OrderException() {
        super(ErrorMessage.ORDER_ERROR.getMessage());
    }

    public OrderException(String message) {
        super(message);
    }

    public OrderException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderException(Throwable cause) {
        super(ErrorMessage.ORDER_ERROR.getMessage(), cause);
    }
}
