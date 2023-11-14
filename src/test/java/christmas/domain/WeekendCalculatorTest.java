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

import static christmas.testconsts.TestConsts.tempDay;
import static christmas.testconsts.TestConsts.tempTotalOrderPrice;
import static org.assertj.core.api.Assertions.assertThat;

class WeekendCalculatorTest {
    private final WeekendCalculator weekendCalculator = new WeekendCalculator();
    private final List<Day> supportedDays = new ArrayList<>();
    private final EnumMap<Menu, Integer> menuCount = new EnumMap<>(Menu.class);

    @BeforeEach
    void initializeSupportedDaysAndMenuesWithoutMain() {
        for (int date = 0; date <= ConstantDate.LAST_DATE.getDate(); date++) {
            Day day = new Day(date);
            if (day.isWeekend()) {
                supportedDays.add(day);
            }
        }
        menuCount.put(Menu.BUTTON_MUSHROOM_SOUP, 1);
        menuCount.put(Menu.TAPAS, 2);
        menuCount.put(Menu.CHOCOLATE_CAKE, 3);
        menuCount.put(Menu.CHAMPAGNE, 2);
    }

    @Test
    @DisplayName("WeekendCalculator는 금요일과 토요일에 기능을 지원할 수 있다.")
    void supports() {
        Money tempMoney = new Money(0);
        supportedDays.forEach(
                day -> assertThat(weekendCalculator.supports(day, tempMoney)).isTrue()
        );
    }

    @Test
    @DisplayName("WeekendCalculator는 계산을 하기 전 방문 예상 날짜가 평일이 아니면 아니라고 말한다.")
    void notSupports() {
        Money tempMoney = new Money(0);
        for (int date = 0; date <= ConstantDate.LAST_DATE.getDate(); date++) {
            Day day = new Day(date);
            if (!supportedDays.contains(day)) {
                assertThat(weekendCalculator.supports(day, tempMoney)).isFalse();
            }
        }
    }

    @Test
    @DisplayName("메인 메뉴 개수만큼 할인 가격이 결정된다.")
    void discountPrice() {
        int tBoneSteakCount = 5;
        menuCount.put(Menu.T_BONE_STEAK, tBoneSteakCount);
        int seafoodPastaCount = 3;
        menuCount.put(Menu.SEAFOOD_PASTA, seafoodPastaCount);

        Order order = new Order(tempDay, menuCount, tempTotalOrderPrice);
        assertThat(weekendCalculator.discountPrice(order))
                .isEqualTo(ConstantMoney.SIGN_INVERTER.getAmount()
                        * ConstantMoney.INCREASE_UNIT_FOR_WEEKDAY_EVENT.getAmount()
                        * (tBoneSteakCount + seafoodPastaCount));
    }

    @Test
    @DisplayName("메인 메뉴가 없으면 할인 가격은 0원이다.")
    void discountNothing() {
        Order order = new Order(tempDay, menuCount, tempTotalOrderPrice);
        assertThat(weekendCalculator.discountPrice(order)).isEqualTo(0);
    }
}