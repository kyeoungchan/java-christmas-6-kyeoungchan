package christmas.domain;

import christmas.consts.ConstantMoney;
import christmas.vo.Day;
import christmas.vo.Money;
import christmas.dto.OrderForEvents;

public class ChristmasDayCalculator implements EventCalculatorAdapter {

    @Override
    public boolean supports(Day day, Money priceBeforeDiscount) {
        return day.isFriday() || day.isSaturday();
    }

    @Override
    public Money discountPrice(OrderForEvents orderForEvents) {
        Day visitingDay = orderForEvents.visitingDay();
        return new Money(calculateDiscountAmount(visitingDay)
                * ConstantMoney.SIGN_INVERTER.getAmount());
    }

    private int calculateDiscountAmount(Day visitingDay) {
        return visitingDay.daysFromFirstDate()
                * ConstantMoney.INCREASE_UNIT_FOR_CHRISTMAS_DAY_EVENT.getAmount()
                + ConstantMoney.INITIAL_DISCOUNT_AMOUNT_FOR_CHRISTMAS_DAY_EVENT.getAmount();
    }
}
