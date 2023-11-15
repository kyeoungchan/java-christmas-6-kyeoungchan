package christmas.domain;

import christmas.consts.Event;
import christmas.consts.Menu;
import christmas.dto.EventsResult;
import christmas.vo.Day;
import christmas.vo.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;

import static org.assertj.core.api.Assertions.assertThat;

class EventCalculatorsManagerTest {
    private final EventCalculatorsManager eventCalculatorsManager = new EventCalculatorsManager();
    private final EnumMap<Menu, Integer> orderMenus = new EnumMap<>(Menu.class);
    private Money totalPrice;

    @BeforeEach
    void initializeOrderMenus() {
        orderMenus.put(Menu.CHAMPAGNE, 1); // 25_000
        orderMenus.put(Menu.TAPAS, 2); // 11_000
        orderMenus.put(Menu.BARBCUE_RIBS, 1); // 54_000
        OrderCalculator orderCalculator = new OrderCalculator();
        totalPrice = orderCalculator.calculateTotalPrice(orderMenus); // 90_000
    }

    @Test
    @DisplayName("1일이면 크리스마스 D-Day 할인과 주말 할인을 적용받고, 총주문 금액이 12만원 미만인 것까지 계산한다.")
    void generateEventsResultFirstDay() {
        Day visitingDay = new Day(1);
        EventsResult eventsResult = eventCalculatorsManager
                .generateEventsResult(visitingDay, orderMenus, totalPrice);
        EnumMap<Event, Money> benefitAmounts = eventsResult.benefitAmounts();
        assertThat(benefitAmounts.keySet().stream().toList())
                .containsExactly(Event.CHRISTMAS_D_DAY_EVENT, Event.WEEKEND_EVENT);
        assertThat(benefitAmounts.values().stream().toList())
                .containsExactly(new Money(-1_000), new Money(-2_023));
    }

    @Test
    @DisplayName("똑같이 1일일 때 총주문 금액이 12만원 이상이면 샴페인도 받는다.")
    void generateEventsResultWithPresent() {
        Day visitingDay = new Day(1);
        orderMenus.put(Menu.RED_WINE, 1); // 60_000
        totalPrice = new Money(totalPrice.amount() + Menu.RED_WINE.getAmount());
        EventsResult eventsResult =
                eventCalculatorsManager.generateEventsResult(visitingDay, orderMenus, totalPrice);
        EnumMap<Event, Money> benefitAmounts = eventsResult.benefitAmounts();
        assertThat(benefitAmounts.keySet().stream().toList())
                .containsExactly(Event.CHRISTMAS_D_DAY_EVENT, Event.WEEKEND_EVENT, Event.PRESENTATION_EVENT);
        assertThat(benefitAmounts.values().stream().toList())
                .containsExactly(new Money(-1_000), new Money(-2_023), new Money(-1 * Menu.CHAMPAGNE.getAmount()));
    }

    @Test
    @DisplayName("25일에는 평일 할인과 특별 할인을 적용받는다.")
    void generateEventResultChristmas() {
        Day visitingDay = new Day(25);
        orderMenus.put(Menu.ICE_CREAM, 1);
        EventsResult eventsResult = eventCalculatorsManager
                .generateEventsResult(visitingDay, orderMenus, totalPrice);
        EnumMap<Event, Money> benefitAmounts = eventsResult.benefitAmounts();
        assertThat(benefitAmounts.keySet().stream().toList())
                .containsExactly(Event.WEEKDAY_EVENT, Event.SPECIAL_EVENT);
        assertThat(benefitAmounts.values().stream().toList())
                .containsExactly(new Money(-2_023), new Money(-1_000));
    }

    @Test
    @DisplayName("25일이어도 디저트 메뉴가 없으면 평일 할인은 해당되지 않는다.")
    void generateEventResultChristmasNoDessert() {
        Day visitingDay = new Day(25);
        EventsResult eventsResult = eventCalculatorsManager
                .generateEventsResult(visitingDay, orderMenus, totalPrice);
        EnumMap<Event, Money> benefitAmounts = eventsResult.benefitAmounts();
        assertThat(benefitAmounts.keySet().stream().toList())
                .containsExactly(Event.SPECIAL_EVENT);
        assertThat(benefitAmounts.values().stream().toList())
                .containsExactly(new Money(-1_000));
    }

    @Test
    @DisplayName("총 얼마를 할인 받았는지도 DTO에 감싸서 반환한다.")
    void generateTotalBenefitAmountFirstDay() {
        Day visitingDay = new Day(1);
        EventsResult eventsResult =
                eventCalculatorsManager.generateEventsResult(visitingDay, orderMenus, totalPrice);
        assertThat(eventsResult.totalBenefitPrice()).isEqualTo(new Money(-30_23));
    }

    @Test
    @DisplayName("샴페인을 증정받으면 증정받은 갯수도 같이 반환한다.")
    void generatePresentCount() {
        Day visitingDay = new Day(1);
        orderMenus.put(Menu.RED_WINE, 1); // 60_000
        int updatedTotalAmount = totalPrice.amount() + Menu.RED_WINE.getAmount();
        totalPrice = new Money(updatedTotalAmount);
        EventsResult eventsResult =
                eventCalculatorsManager.generateEventsResult(visitingDay, orderMenus, totalPrice);
        EnumMap<Menu, Integer> presentationCount = eventsResult.presentationCount();
        assertThat(presentationCount.keySet()).containsOnly(Menu.CHAMPAGNE);
        assertThat(presentationCount.values()).containsOnly(1);
    }

    @Test
    @DisplayName("샴페인을 증정받지 못하면 빈 EnumMap을 반환한다.")
    void generateNonePresentCount() {
        Day visitingDay = new Day(1);
        EventsResult eventsResult =
                eventCalculatorsManager.generateEventsResult(visitingDay, orderMenus, totalPrice);
        EnumMap<Menu, Integer> presentationCount = eventsResult.presentationCount();
        assertThat(presentationCount.keySet()).doesNotContain(Menu.CHAMPAGNE);
        assertThat(presentationCount.keySet()).isEmpty();
        assertThat(presentationCount.values()).isEmpty();
    }
}