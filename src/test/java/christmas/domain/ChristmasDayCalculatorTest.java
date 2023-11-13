package christmas.domain;

import christmas.consts.ConstantDate;
import christmas.vo.Day;
import christmas.vo.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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
        Money tempMoney = new Money();
        assertThat(christmasDayCalculator.supports(first, tempMoney))
                .isTrue();
    }

    @Test
    @DisplayName("금요일 혹은 토요일이 아닌 날짜를 입력하면 적용하지 않음을 나타낸다.")
    void notSupportsFifthDate() {
        Day fifth = new Day(5);
        Money tempMoney = new Money();
        assertThat(christmasDayCalculator.supports(fifth, tempMoney))
                .isFalse();
    }

    @Test
    @DisplayName("ChristmasDayCalculator는 계산을 하기 전 방문 예상 날짜가 금요일 혹은 토요일인지 먼저 계산한다.")
    void supports() {
        Money tempMoney = new Money();
        supportedDays.forEach(
                day -> assertThat(christmasDayCalculator.supports(day, tempMoney)).isTrue()
        );
    }

    @Test
    @DisplayName("ChristmasDayCalculator는 계산을 하기 전 방문 예상 날짜가 금요일 혹은 토요일이 아니면 아니라고 말한다.")
    void notSupports() {
        Money tempMoney = new Money();
        for (int date = 0; date <= ConstantDate.LAST_DATE.getDate(); date++) {
            Day day = new Day(date);
            if (!supportedDays.contains(day)) {
                assertThat(christmasDayCalculator.supports(day, tempMoney)).isFalse();
            }
        }
    }
}