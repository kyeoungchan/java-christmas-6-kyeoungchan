package christmas.service;

import christmas.consts.ConstantMoney;
import christmas.consts.Event;
import christmas.consts.Menu;
import christmas.domain.OrderMenus;
import christmas.dto.CalculateResult;
import christmas.vo.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;

import static christmas.testconsts.TestConsts.tempDay;
import static org.assertj.core.api.Assertions.assertThat;

class BenefitCalculatorTest {
    private final BenefitCalculator benefitCalculator = new BenefitCalculator();
    private final EnumMap<Menu, Integer> menuCount = new EnumMap<>(Menu.class);
    private CalculateResult calculateResult;

    @BeforeEach
    void initializeOrderMenus() {
        menuCount.put(Menu.BARBCUE_RIBS, 1);
        OrderMenus orderMenus = new OrderMenus(menuCount);
        calculateResult = benefitCalculator.generateBenefitDetails(tempDay, orderMenus);
    }

    @Test
    @DisplayName("BenefitCalculator는 주문 정보도 반환한다.")
    void generateOrderMenus() {
        assertThat(calculateResult.orderMenus().keySet().stream().toList())
                .containsOnly(Menu.BARBCUE_RIBS);
        assertThat(calculateResult.orderMenus().values().stream().toList())
                .containsOnly(1);
    }

    @Test
    @DisplayName("BenefitCalculator는 할인 적용 전 총주문 금액도 반환한다.")
    void generateBenefitDetails() {
        assertThat(calculateResult.totalPriceBeforeDiscount().getAmount())
                .isEqualTo(Menu.BARBCUE_RIBS.getAmount());
    }

    @Test
    @DisplayName("BenefitCalculator는 할인별 혜택 금액들도 반환한다.")
    void generateBenefitAmounts() {
        EnumMap<Event, Money> benefitAmounts = calculateResult.eventsResult().benefitAmounts();
        assertThat(benefitAmounts.keySet().stream().toList())
                .containsExactly(Event.CHRISTMAS_D_DAY_EVENT, Event.WEEKEND_EVENT);
        Money christmasAppliedAmount = new Money(ConstantMoney.SIGN_INVERTER.getAmount() * 1_000);
        Money weekdayAppliedAmount = new Money(ConstantMoney.SIGN_INVERTER.getAmount() * 2_023);
        assertThat(benefitAmounts.values().stream().toList())
                .containsExactly(christmasAppliedAmount, weekdayAppliedAmount);
    }

    @Test
    @DisplayName("BenefitCalculator는 증정 상품에 대한 결과도 반환한다.")
    void generatePresentationCount() {
        menuCount.put(Menu.RED_WINE, 2); // 60_000 * 2
        OrderMenus orderMenus = new OrderMenus(menuCount);
        calculateResult = benefitCalculator.generateBenefitDetails(tempDay, orderMenus);
        EnumMap<Menu, Integer> presentationCount = calculateResult.eventsResult().presentationCount();
        assertThat(presentationCount.keySet()).containsOnly(Menu.CHAMPAGNE);
        assertThat(presentationCount.values()).containsOnly(1);
    }

    @Test
    @DisplayName("BenefitCalculator는 증정 상품에 대한 결과가 없으면 빈 컬렉션을 반환한다.")
    void generateEmptyPresentationCount() {
        EnumMap<Menu, Integer> presentationCount = calculateResult.eventsResult().presentationCount();
        assertThat(presentationCount).isEmpty();
        assertThat(presentationCount.keySet()).isEmpty();
        assertThat(presentationCount.values()).isEmpty();
    }

    @Test
    @DisplayName("BenefitCalculator는 총혜택 금액도 반환한다.")
    void generateTotalBenefitPrice() {
        assertThat(calculateResult.eventsResult().totalBenefitPrice())
                .isEqualTo(new Money(ConstantMoney.SIGN_INVERTER.getAmount() * 3_023));
    }

    @Test
    @DisplayName("BenefitCalculator는 혜택받은 후 총주문 금액도 반환한다.")
    void generateTotalPriceAfterDiscount() {
        assertThat(calculateResult.totalPriceAfterDiscount())
                .isEqualTo(new Money(Menu.BARBCUE_RIBS.getAmount() - 3_023));
    }

    @Test
    @DisplayName("처음부터 할인 혜택을 못 받을 고객이라면 빈 컬렉션과 0원의 혜택 금액을 반환한다.")
    void generateNoneEventResult() {
        menuCount.remove(Menu.BARBCUE_RIBS);
        OrderMenus orderMenus = new OrderMenus(menuCount);
        calculateResult = benefitCalculator.generateBenefitDetails(tempDay, orderMenus);
        assertThat(calculateResult.eventsResult().benefitAmounts()).isEmpty();
        assertThat(calculateResult.eventsResult().presentationCount()).isEmpty();
        assertThat(calculateResult.eventsResult().totalBenefitPrice()).isEqualTo(new Money(0));
    }
}