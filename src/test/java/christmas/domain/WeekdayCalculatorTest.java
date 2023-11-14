package christmas.domain;

import christmas.consts.ConstantDate;
import christmas.consts.ConstantMoney;
import christmas.consts.Menu;
import christmas.dto.OrderForEvents;
import christmas.vo.Day;
import christmas.vo.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

import static christmas.testconsts.TestConsts.tempDay;
import static christmas.testconsts.TestConsts.tempTotalOrderPrice;
import static org.assertj.core.api.Assertions.assertThat;

class WeekdayCalculatorTest {
    private final WeekdayCalculator weekdayCalculator = new WeekdayCalculator();
    private final List<Day> supportedDays = new ArrayList<>();
    private final List<Menu> menus = new ArrayList<>();
    private final EnumMap<Menu, Integer> menuCount = new EnumMap<>(Menu.class);

    @BeforeEach
    void initializeData() {
        initializeSupportedDays();
        initializeMenusNoDessert();
        initializeMenuCountNoDessert();
    }

    private void initializeSupportedDays() {
        for (int date = 0; date <= ConstantDate.LAST_DATE.getDate(); date++) {
            Day day = new Day(date);
            if (day.isWeekday()) {
                supportedDays.add(day);
            }
        }
    }

    private void initializeMenusNoDessert() {
        menus.add(Menu.T_BONE_STEAK);
        menus.add(Menu.BUTTON_MUSHROOM_SOUP);
        menus.add(Menu.TAPAS);
        menus.add(Menu.COKE_ZERO);
    }

    private void initializeMenuCountNoDessert() {
        menuCount.put(Menu.T_BONE_STEAK, 2);
        menuCount.put(Menu.BUTTON_MUSHROOM_SOUP, 3);
        menuCount.put(Menu.TAPAS, 4);
        menuCount.put(Menu.COKE_ZERO, 3);
    }

    @Test
    @DisplayName("WeekdayCalculator는 일요일에서 목요일은 기능을 지원할 수 있다.")
    void supportedDay() {
        Money tempMoney = new Money(0);
        menus.add(Menu.ICE_CREAM);
        supportedDays.forEach(
                day -> assertThat(weekdayCalculator.supports(day, tempMoney, menus)).isTrue()
        );
    }

    @Test
    @DisplayName("평일이어도 디저트를 주문하지 않으면 지원하지 않는다.")
    void supportedDayNotDessert() {
        Money tempMoney = new Money(0);
        supportedDays.forEach(
                day -> assertThat(weekdayCalculator.supports(day, tempMoney, menus)).isFalse()
        );
    }

    @Test
    @DisplayName("WeekdayCalculator는 계산을 하기 전 방문 예상 날짜가 평일이 아니면 아니라고 말한다.")
    void notSupports() {
        Money tempMoney = new Money(0);
        for (int date = 0; date <= ConstantDate.LAST_DATE.getDate(); date++) {
            Day day = new Day(date);
            if (!supportedDays.contains(day)) {
                assertThat(weekdayCalculator.supports(day, tempMoney, menus)).isFalse();
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

        OrderForEvents orderForEvents = new OrderForEvents(tempDay, menuCount, tempTotalOrderPrice);
        Money expectedPrice = new Money(ConstantMoney.SIGN_INVERTER.getAmount()
                * ConstantMoney.INCREASE_UNIT_FOR_WEEKDAY_EVENT.getAmount()
                * (chocolateCakeCount + iceCreamCount));
        assertThat(weekdayCalculator.discountPrice(orderForEvents)).isEqualTo(expectedPrice);
    }

    @Test
    @DisplayName("디저트 메뉴가 없으면 할인 가격은 0원이다.")
    void discountNothing() {
        OrderForEvents orderForEvents = new OrderForEvents(tempDay, menuCount, tempTotalOrderPrice);
        assertThat(weekdayCalculator.discountPrice(orderForEvents)).isEqualTo(new Money(0));
    }
}