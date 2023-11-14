package christmas.domain;

import christmas.consts.ConstantMoney;
import christmas.consts.Menu;
import christmas.dto.OrderForEvents;
import christmas.vo.Day;
import christmas.vo.Money;

import java.util.List;

public class PresentationCalculator implements EventCalculatorAdapter {
    @Override
    public boolean supports(Day day, Money priceBeforeDiscount, List<Menu> menus) {
        return priceBeforeDiscount.getAmount()
                >= ConstantMoney.STANDARD_FOR_PRESENTATION_EVENT.getAmount();
    }

    @Override
    public Money discountPrice(OrderForEvents orderForEvents) {
        int presentationCount = countPresentation(orderForEvents);
        return new Money(presentationCount * Menu.CHAMPAGNE.getAmount()
                * ConstantMoney.SIGN_INVERTER.getAmount());
    }

    private int countPresentation(OrderForEvents orderForEvents) {
        int totalOrderAmount = orderForEvents.totalOrderPrice().getAmount();
        return totalOrderAmount / ConstantMoney.STANDARD_FOR_PRESENTATION_EVENT.getAmount();
    }
}
