package christmas.vo;

import christmas.consts.ConstantDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DayTest {
    private final List<Integer> fridays = List.of(1, 8, 15, 22, 29);
    private final List<Integer> saturdays = List.of(2, 9, 16, 23, 30);
    private final List<Integer> allDays = new ArrayList<>();

    @BeforeEach
    void initializeAllDays() {
        for (int date = 1; date <= ConstantDate.LAST_DATE.getDate(); date++) {
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
}