package christmas.domain;

import christmas.consts.ConstantMoney;
import christmas.consts.Menu;
import christmas.vo.Day;
import christmas.vo.Money;
import christmas.vo.Order;

public class PresentationCalculator implements EventCalculatorAdapter {
    @Override
    public boolean supports(Day day, Money priceBeforeDiscount) {
        return priceBeforeDiscount.getAmount()
                >= ConstantMoney.STANDARD_FOR_PRESENTATION_EVENT.getAmount();
    }

    @Override
    public int discountPrice(Order order) {
        int presentationCount = countPresentation(order);
        return presentationCount * Menu.CHAMPAGNE.getAmount()
                * ConstantMoney.SIGN_INVERTER.getAmount();
    }

    private int countPresentation(Order order) {
        int totalOrderAmount = order.totalOrderPrice().getAmount();
        return totalOrderAmount / ConstantMoney.STANDARD_FOR_PRESENTATION_EVENT.getAmount();
    }
}
