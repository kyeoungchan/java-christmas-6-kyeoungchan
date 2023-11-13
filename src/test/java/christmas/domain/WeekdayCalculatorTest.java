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

class WeekdayCalculatorTest {
    private final WeekdayCalculator weekdayCalculator = new WeekdayCalculator();
    private final List<Day> supportedDays = new ArrayList<>();

    @BeforeEach
    void initializeSupportedDays() {
        for (int date = 0; date <= ConstantDate.LAST_DATE.getDate(); date++) {
            Day day = new Day(date);
            if (day.isWeekday()) {
                supportedDays.add(day);
            }
        }
    }

    @Test
    @DisplayName("WeekdayCalculator는 일요일에서 목요일은 기능을 지원할 수 있다.")
    void supports() {
        Money tempMoney = new Money(0);
        supportedDays.forEach(
                day -> assertThat(weekdayCalculator.supports(day, tempMoney)).isTrue())
        ;
    }

    @Test
    @DisplayName("WeekdayCalculator는 계산을 하기 전 방문 예상 날짜가 평일이 아니면 아니라고 말한다.")
    void notSupports() {
        Money tempMoney = new Money(0);
        for (int date = 0; date <= ConstantDate.LAST_DATE.getDate(); date++) {
            Day day = new Day(date);
            if (!supportedDays.contains(day)) {
                assertThat(weekdayCalculator.supports(day, tempMoney)).isFalse();
            }
        }
    }

}