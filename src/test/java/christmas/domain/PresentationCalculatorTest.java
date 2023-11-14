package christmas.domain;

import christmas.consts.ConstantMoney;
import christmas.consts.Menu;
import christmas.testconsts.TestConsts;
import christmas.vo.Day;
import christmas.vo.Money;
import christmas.vo.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static christmas.testconsts.TestConsts.tempDay;
import static christmas.testconsts.TestConsts.tempMenuCounts;
import static org.assertj.core.api.Assertions.assertThat;

class PresentationCalculatorTest {
    private final PresentationCalculator presentationCalculator = new PresentationCalculator();

    @Test
    @DisplayName("PresentationCalculator는 총주문 가격이 12만원 이상이면 기능을 지원한다.")
    void supports() {
        int moneyIncreaseUnit = 100;
        int tempLoopCount = 1000;
        Day tempDay = new Day(1);
        for (int i = 0; i < tempLoopCount; i++) {
            int totalAmount = ConstantMoney.STANDARD_FOR_PRESENTATION_EVENT.getAmount()
                    + moneyIncreaseUnit * i; // 12만원 + 100원 * i
            assertThat(presentationCalculator.supports(tempDay, new Money(totalAmount)))
                    .isTrue();
        }
    }

    @Test
    @DisplayName("PresentationCalculator는 총주문 가격이 12만원 이하면 기능을 지원하지 않는다.")
    void notSupports() {
        int minimumPrice = Menu.COKE_ZERO.getAmount(); // 메뉴 중에서 제로 콜라가 제일 싸다.
        int moneyIncreaseUnit = 100;
        int loopCount = (ConstantMoney.STANDARD_FOR_PRESENTATION_EVENT.getAmount() - minimumPrice)
                / moneyIncreaseUnit; // (12만원 - 최소 주문 금액) / 100원
        Day tempDay = new Day(1);
        for (int i = 0; i < loopCount; i++) {
            int totalAmount = minimumPrice + moneyIncreaseUnit * i; // 최소 주문 금액 + 100원 * i
            assertThat(presentationCalculator.supports(tempDay, new Money(totalAmount))).isFalse();
        }
    }

    @Test
    @DisplayName("증정 대상이라면 12만원에 한 개의 샴페인 가격만큼 혜택을 준다.")
    void discountPrice() {
        int moneyIncreaseUnit = 100;
        int loopCount = ConstantMoney.STANDARD_FOR_PRESENTATION_EVENT.getAmount() / moneyIncreaseUnit;
        for (int i = 0; i < loopCount; i++) {
            Money totalOrderPrice = new Money( // 12만원 + (100원 * i)
                    ConstantMoney.STANDARD_FOR_PRESENTATION_EVENT.getAmount() + moneyIncreaseUnit * i);
            Order order = new Order(tempDay, tempMenuCounts, totalOrderPrice);
            assertThat(presentationCalculator.discountPrice(order)).isEqualTo(
                    ConstantMoney.SIGN_INVERTER.getAmount() * Menu.CHAMPAGNE.getAmount());
        }
    }

    @Test
    @DisplayName("12만원 배수만큼 주문하면 그 배수만큼의 샴페인 가격만큼 혜택을 준다.")
    void discountPriceForMultiple() {
        int loopCount = 10;
        for (int multipleUnit = 1; multipleUnit <= loopCount; multipleUnit++) {
            Money totalOrderPrice = new Money( // 12만원 * i
                    ConstantMoney.STANDARD_FOR_PRESENTATION_EVENT.getAmount() * multipleUnit);
            Order order = new Order(tempDay, tempMenuCounts, totalOrderPrice);
            assertThat(presentationCalculator.discountPrice(order)).isEqualTo(
                    ConstantMoney.SIGN_INVERTER.getAmount() * Menu.CHAMPAGNE.getAmount()
                            * multipleUnit
            );
        }
    }
}