package christmas.domain;

import christmas.consts.ConstantDate;
import christmas.consts.ConstantMoney;
import christmas.consts.Menu;
import christmas.vo.Day;
import christmas.vo.Money;
import christmas.vo.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class WeekdayCalculatorTest {
    private final WeekdayCalculator weekdayCalculator = new WeekdayCalculator();
    private final List<Day> supportedDays = new ArrayList<>();
    private final EnumMap<Menu, Integer> menuCount = new EnumMap<>(Menu.class);

    @BeforeEach
    void initializeSupportedDaysAndMenuesWithoutDessert() {
        for (int date = 0; date <= ConstantDate.LAST_DATE.getDate(); date++) {
            Day day = new Day(date);
            if (day.isWeekday()) {
                supportedDays.add(day);
            }
        }
        menuCount.put(Menu.T_BONE_STEAK, 2);
        menuCount.put(Menu.BUTTON_MUSHROOM_SOUP, 3);
        menuCount.put(Menu.TAPAS, 4);
        menuCount.put(Menu.COKE_ZERO, 3);
    }

    @Test
    @DisplayName("WeekdayCalculator는 일요일에서 목요일은 기능을 지원할 수 있다.")
    void supports() {
        Money tempMoney = new Money(0);
        supportedDays.forEach(
                day -> assertThat(weekdayCalculator.supports(day, tempMoney)).isTrue()
        );
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

    @Test
    @DisplayName("디저트 메뉴 개수만큼 할인 가격이 결정된다.")
    void discountPrice() {
        int chocolateCakeCount = 5;
        menuCount.put(Menu.CHOCOLATE_CAKE, chocolateCakeCount);
        int iceCreamCount = 3;
        menuCount.put(Menu.ICE_CREAM, iceCreamCount);

        Order order = new Order(new Day(1), menuCount);
        assertThat(weekdayCalculator.discountPrice(order))
                .isEqualTo(-1 * ConstantMoney.INCREASE_UNIT_FOR_WEEKDAY_EVENT.getAmount()
                        * (chocolateCakeCount + iceCreamCount));
    }

    @Test
    @DisplayName("디저트 메뉴가 없으면 할인 가격은 0원이다.")
    void discountNothing() {
        Order order = new Order(new Day(1), menuCount);
        assertThat(weekdayCalculator.discountPrice(order)).isEqualTo(0);
    }
}