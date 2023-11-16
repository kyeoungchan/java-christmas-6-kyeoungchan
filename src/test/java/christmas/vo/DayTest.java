package christmas.vo;

import christmas.consts.ConstantDate;
import christmas.consts.ErrorMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DayTest {
    private final List<Integer> fridays = List.of(1, 8, 15, 22, 29);
    private final List<Integer> saturdays = List.of(2, 9, 16, 23, 30);
    private final List<Integer> sundays = List.of(3, 10, 17, 24, 31);
    private final List<Integer> allDays = new ArrayList<>();

    @BeforeEach
    void initializeAllDays() {
        for (int date = 1; date <= ConstantDate.LAST.getDate(); date++) {
            allDays.add(date);
        }
    }

    @Test
    @DisplayName("12월은 1, 8, 15, 22, 29일이 금요일이다.")
    void isFriday() {
        fridays.forEach(friday -> {
            Day day = new Day(friday);
            assertThat(day.isFriday()).isTrue();
        });
    }

    @Test
    @DisplayName("12월은 1, 8, 15, 22, 29 외에는 금요일이 아니다.")
    void isNotFriday() {
        allDays.stream()
                .filter(date -> !fridays.contains(date))
                .forEach(date -> assertThat(new Day(date).isFriday()).isFalse());
    }

    @Test
    @DisplayName("12월은 2, 9, 16, 23, 30일이 토요일이다.")
    void isSaturday() {
        saturdays.forEach(saturday -> {
            Day day = new Day(saturday);
            assertThat(day.isSaturday()).isTrue();
        });
    }

    @Test
    @DisplayName("12월은 2, 9, 16, 23, 30일 외에는 토요일이 아니다.")
    void isNotSaturday() {
        allDays.stream()
                .filter(date -> !saturdays.contains(date))
                .forEach(date -> assertThat(new Day(date).isSaturday()).isFalse());
    }

    @Test
    @DisplayName("금요일과 토요일이 아니면 주중이다.")
    void isWeekday() {
        allDays.stream()
                .filter(date -> !fridays.contains(date) && !saturdays.contains(date))
                .forEach(date -> assertThat(new Day(date).isWeekday()).isTrue());
    }

    @Test
    @DisplayName("금요일과 토요일은 주말이다.")
    void isWeekend() {
        allDays.stream()
                .filter(date -> fridays.contains(date) || saturdays.contains(date))
                .forEach(date -> assertThat(new Day(date).isWeekend()).isTrue());
    }

    @Test
    @DisplayName("1일부터 며칠이 지났는지 알려준다.")
    void daysFromFirstDate() {
        int firstDate = 1;
        allDays.forEach(date -> assertThat(new Day(date).daysFromFirstDate())
                .isEqualTo(date - firstDate));
    }

    @Test
    @DisplayName("12월은 3, 10, 17, 24, 31일이 일요일이다.")
    void isSunday() {
        sundays.forEach(saturday -> {
            Day day = new Day(saturday);
            assertThat(day.isSunday()).isTrue();
        });
    }

    @Test
    @DisplayName("12월은 3, 10, 17, 24, 31일 외에는 일요일이 아니다.")
    void isNotSunday() {
        allDays.stream()
                .filter(date -> !sundays.contains(date))
                .forEach(date -> assertThat(new Day(date).isSunday()).isFalse());
    }

    @Test
    @DisplayName("12월 25일은 크리스마스 디데이다.")
    void isChristmasDDay() {
        int christmasDate = 25;
        assertThat(new Day(christmasDate).isChristmasDDay()).isTrue();
    }

    @Test
    @DisplayName("1보다 작은 숫자의 날짜를 입력받으면 예외를 발생시킨다.")
    void tooSmallDateInput() {
        int loopCount = 10;
        for (int i = 1; i <= loopCount; i++) {
            int tooSmallDate = ConstantDate.FIRST.getDate() - i;
            assertThatThrownBy(() -> new Day(tooSmallDate)).isExactlyInstanceOf(IllegalArgumentException.class);
            assertThatThrownBy(() -> new Day(tooSmallDate)).hasMessageContaining(
                    ErrorMessage.ERROR_PREFIX.getMessage(), ErrorMessage.ERROR_POSTFIX, ErrorMessage.UNVALIDATED_DATE
            );
        }
    }

    @Test
    @DisplayName("1일~31일 이외의 날짜를 입력받으면 예외를 발생시킨다.")
    void tooBigDateInput() {
        int loopCount = 10;
        for (int i = 1; i <= loopCount; i++) {
            int tooBigDate = ConstantDate.LAST.getDate() + i;
            assertThatThrownBy(() -> new Day(tooBigDate)).isExactlyInstanceOf(IllegalArgumentException.class);
            assertThatThrownBy(() -> new Day(tooBigDate)).hasMessageContaining(
                    ErrorMessage.ERROR_PREFIX.getMessage(), ErrorMessage.ERROR_POSTFIX, ErrorMessage.UNVALIDATED_DATE
            );
        }
    }
}