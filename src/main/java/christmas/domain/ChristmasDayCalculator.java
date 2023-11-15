package christmas.domain;

import christmas.consts.ConstantMoney;
import christmas.consts.Menu;
import christmas.vo.Day;
import christmas.vo.Money;
import christmas.dto.OrderForEvents;

import java.util.List;

public class ChristmasDayCalculator implements EventCalculatorAdapter {
    @Override
    public boolean supports(Day day, Money priceBeforeDiscount, List<Menu> menus) {
        // 크리스마스 D-Day 할인 혜택은 day에서 예외가 발생하지 않는 한 항상 적용
        return true;
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
