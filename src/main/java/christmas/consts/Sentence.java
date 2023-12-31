package christmas.consts;

public enum Sentence {
    INTRODUCING_EVENT_PLANNER("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."),
    ASKING_DAY_INPUT("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"),
    ASKING_ORDER_MENUS_INPUT("주문하실 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)"),
    INTRODUCING_EVENTS("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"),
    ORDER_MENU_PREVIEW("<주문 메뉴>"),
    TOTAL_PRICE_BEFORE_DISCOUNT_PREVIEW("<할인 전 총주문 금액>"),
    PRESENTATION_PREVIEW("<증정 메뉴>"),
    BENEFIT_DETAILS_PREVIEW("<혜택 내역>"),
    TOTAL_BENEFIT_AMOUNT_PREVIEW("<총혜택 금액>"),
    TOTAL_PRICE_AFTER_DISCOUNT_PREVIEW("<할인 후 예상 결제 금액>"),
    GIVING_BADGE_PREVIEW("<12월 이벤트 배지>"),
    NOTHING("없음");

    private final String message;

    Sentence(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getMessage(int value) {
        return String.format(message, value);
    }
}
