package christmas.domain;

import christmas.consts.ConstantDate;
import christmas.consts.ConstantMoney;
import christmas.vo.Day;
import christmas.vo.Money;
import christmas.vo.Order;

public class ChristmasDayCalculator implements EventCalculatorAdapter {

    @Override
    public boolean supports(Day day, Money priceBeforeDiscount) {
        return day.isFriday() || day.isSaturday();
    }

    @Override
    public int discountPrice(Order order) {
        Day visitingDay = order.visitingDay();
        return -1 * calculateDiscountAmount(visitingDay);
    }

    private int calculateDiscountAmount(Day visitingDay) {
        return (visitingDay.getDate() - ConstantDate.CALCULATE_UNIT_FOR_CHRISTMAS_DAY_EVENT.getDate())
                * ConstantMoney.INCREASE_UNIT_FOR_CHRISTMAS_DAY_EVENT.getAmount()
                + ConstantMoney.INITIAL_DISCOUNT_AMOUNT_FOR_CHRISTMAS_DAY_EVENT.getAmount();
    }
}
