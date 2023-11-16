package christmas.domain;

import christmas.consts.ConstantMoney;
import christmas.consts.Menu;
import christmas.vo.Day;
import christmas.vo.Money;
import christmas.dto.OrderForEvents;

import java.util.List;

public class SpecialCalculator implements EventCalculatorAdapter {
    @Override
    public boolean supports(Day day, Money priceBeforeDiscount, List<Menu> menus) {
        return day.isSunday() || day.isChristmasDDay();
    }

    @Override
    public Money discountPrice(OrderForEvents orderForEvents) {
        return new Money(ConstantMoney.SINGLE_UNIT_FOR_SPECIAL_EVENT.getAmount()
                * ConstantMoney.SIGN_INVERTER.getAmount());
    }
}
