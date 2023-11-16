package christmas.domain;

import christmas.consts.ConstantDate;
import christmas.consts.ConstantMoney;
import christmas.vo.Day;
import christmas.vo.Money;
import christmas.dto.OrderForEvents;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static christmas.testconsts.TestConsts.*;
import static org.assertj.core.api.Assertions.assertThat;

class SpecialCalculatorTest {
    private final SpecialCalculator specialCalculator = new SpecialCalculator();
    private final List<Day> supportedDays = new ArrayList<>();

    @BeforeEach
    void initializeSupportedDays() {
        for (int date = ConstantDate.FIRST.getDate(); date <= ConstantDate.LAST.getDate(); date++) {
            Day day = new Day(date);
            if (day.isSunday() || day.isChristmasDDay()) {
                supportedDays.add(day);
            }
        }
    }

    @Test
    @DisplayName("SpecialCalculator는 일요일 혹은 크리스마스에 기능을 지원할 수 있다.")
    void supports() {
        Money tempMoney = new Money(0);
        supportedDays.forEach(
                day -> assertThat(specialCalculator.supports(day, tempMoney, tempMenus)).isTrue()
        );
    }

    @Test
    @DisplayName("SpecialCalculator는 계산을 하기 전 방문 예상 날짜가 평일이 아니면 아니라고 말한다.")
    void notSupports() {
        Money tempMoney = new Money(0);
        for (int date = ConstantDate.FIRST.getDate(); date <= ConstantDate.LAST.getDate(); date++) {
            Day day = new Day(date);
            if (!supportedDays.contains(day)) {
                assertThat(specialCalculator.supports(day, tempMoney, tempMenus)).isFalse();
            }
        }
    }

    @Test
    @DisplayName("할인 가격은 날짜가 해당되기만 하면 총 1,000원이다.")
    void discountPrice() {
        Day tempVisitingDay = supportedDays.get(0);
        OrderForEvents orderForEvents = new OrderForEvents(tempVisitingDay, tempMenuCounts, tempTotalOrderPrice);
        Money expectedPrice = new Money(ConstantMoney.SIGN_INVERTER.getAmount()
                * ConstantMoney.SINGLE_UNIT_FOR_SPECIAL_EVENT.getAmount());
        assertThat(specialCalculator.discountPrice(orderForEvents)).isEqualTo(expectedPrice);
    }
}