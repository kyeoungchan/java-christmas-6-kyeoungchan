package christmas.consts;

public enum ErrorMessage {
    ERROR_PREFIX("[ERROR] "),
    ERROR_POSTFIX(" 다시 입력해 주세요."),
    UNVALIDATED_ORDER("유효하지 않은 주문입니다."),
    NOT_INPUTTED_MENU_AND_COUNT("메뉴와 개수가 입력되지 않았습니다."),
    ORDERED_ONLY_BEVERAGE("음료만 주문할 수 없습니다."),
    ORDERED_TOO_MUCH("메뉴는 최대 20개까지 주문할 수 있습니다."),
    UNVALIDATED_DATE("유효하지 않은 날짜입니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
