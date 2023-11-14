package christmas.domain;

import christmas.consts.ConstantMoney;
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
        return 0;
    }
}
