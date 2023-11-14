package christmas.domain;

import christmas.consts.ConstantMoney;
import christmas.vo.Day;
import christmas.vo.Money;
import christmas.dto.OrderForEvents;

public class SpecialCalculator implements EventCalculatorAdapter {
    @Override
    public boolean supports(Day day, Money priceBeforeDiscount) {
        return day.isSunday() || day.isChristmasDDay();
    }

    @Override
    public int discountPrice(OrderForEvents orderForEvents) {
        return ConstantMoney.SINGLE_UNIT_FOR_SPECIAL_EVENT.getAmount()
                * ConstantMoney.SIGN_INVERTER.getAmount();
    }
}
