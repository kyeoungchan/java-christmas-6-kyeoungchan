package christmas.domain;

import christmas.consts.ConstantDate;
import christmas.vo.Day;
import christmas.vo.Money;
import christmas.vo.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static christmas.testconsts.TestConsts.tempMenuCounts;
import static christmas.testconsts.TestConsts.tempTotalOrderPrice;
import static org.assertj.core.api.Assertions.assertThat;

class ChristmasDayCalculatorTest {
    private final ChristmasDayCalculator christmasDayCalculator = new ChristmasDayCalculator();
    private final List<Day> supportedDays = new ArrayList<>();

    @BeforeEach
    void initializeSupportedDays() {
        for (int date = 0; date <= ConstantDate.LAST_DATE.getDate(); date++) {
            Day day = new Day(date);
            if (day.isFriday() || day.isSaturday()) {
                supportedDays.add(day);
            }
        }
    }

    @Test
    @DisplayName("올해 금요일인 1일은 적용함을 나타낸다.")
    void supportsFirstDate() {
        Day first = new Day(1);
        Money tempMoney = new Money(0);
        assertThat(christmasDayCalculator.supports(first, tempMoney))
                .isTrue();
    }

    @Test
    @DisplayName("금요일 혹은 토요일이 아닌 날짜를 입력하면 적용하지 않음을 나타낸다.")
    void notSupportsFifthDate() {
        Day fifth = new Day(5);
        Money tempMoney = new Money(0);
        assertThat(christmasDayCalculator.supports(fifth, tempMoney))
                .isFalse();
    }

    @Test
    @DisplayName("ChristmasDayCalculator는 계산을 하기 전 방문 예상 날짜가 금요일 혹은 토요일인지 먼저 계산한다.")
    void supports() {
        Money tempMoney = new Money(0);
        supportedDays.forEach(
                day -> assertThat(christmasDayCalculator.supports(day, tempMoney)).isTrue()
        );
    }

    @Test
    @DisplayName("ChristmasDayCalculator는 계산을 하기 전 방문 예상 날짜가 금요일 혹은 토요일이 아니면 아니라고 말한다.")
    void notSupports() {
        Money tempMoney = new Money(0);
        for (int date = 0; date <= ConstantDate.LAST_DATE.getDate(); date++) {
            Day day = new Day(date);
            if (!supportedDays.contains(day)) {
                assertThat(christmasDayCalculator.supports(day, tempMoney)).isFalse();
            }
        }
    }

    @Test
    @DisplayName("1일이면 1,000원을 할인해준다. 참고로 메뉴 종류는 크리스마스 디데이 이벤트에 관여하지 않는다.")
    void discountFirstDate() {
        Order order = new Order(new Day(1), tempMenuCounts, tempTotalOrderPrice);
        int expectedDiscountAmount = 1_000;
        assertThat(christmasDayCalculator.discountPrice(order))
                .isEqualTo(-1 * expectedDiscountAmount);
    }

    @Test
    @DisplayName("2일이면 1,100원을 할인해준다. 참고로 메뉴 종류는 크리스마스 디데이 이벤트에 관여하지 않는다.")
    void discountSecondDate() {
        Order order = new Order(new Day(2), tempMenuCounts, tempTotalOrderPrice);
        int expectedDiscountAmount = 1_100;
        assertThat(christmasDayCalculator.discountPrice(order))
                .isEqualTo(-1 * expectedDiscountAmount);
    }

    @Test
    @DisplayName("2일이면 1,100원을 할인해준다. 참고로 메뉴 종류는 크리스마스 디데이 이벤트에 관여하지 않는다.")
    void discountEighthDate() {
        Order order = new Order(new Day(8), tempMenuCounts, tempTotalOrderPrice);
        int expectedDiscountAmount = 1_700;
        assertThat(christmasDayCalculator.discountPrice(order))
                .isEqualTo(-1 * expectedDiscountAmount);
    }

    @Test
    @DisplayName("25일이면 3,400원을 할인해준다. 참고로 메뉴 종류는 크리스마스 디데이 이벤트에 관여하지 않는다.")
    void discountTwentyFifthDate() {
        Order order = new Order(new Day(25), tempMenuCounts, tempTotalOrderPrice);
        int expectedDiscountAmount = 3_400;
        assertThat(christmasDayCalculator.discountPrice(order))
                .isEqualTo(-1 * expectedDiscountAmount);
    }
}