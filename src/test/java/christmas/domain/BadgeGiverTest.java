package christmas.domain;

import christmas.consts.Badge;
import christmas.vo.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BadgeGiverTest {

    private final BadgeGiver badgeGiver = new BadgeGiver();

    @Test
    @DisplayName("총혜택 금액이 20_000원 이상이면 산타 배지를 준다.")
    void getSantaBadge() {
        Money totalBenefitAmount = new Money(20_000);
        assertThat(badgeGiver.getRightBadge(totalBenefitAmount))
                .isEqualTo(Badge.SANTA);
    }

    @Test
    @DisplayName("총혜택 금액이 10_000원 이상이면 트리 배지를 준다.")
    void getTreeBadge() {
        Money totalBenefitAmount = new Money(10_000);
        assertThat(badgeGiver.getRightBadge(totalBenefitAmount))
                .isEqualTo(Badge.TREE);
    }
    @Test
    @DisplayName("총혜택 금액이 5_000원 이상이면 별 배지를 준다.")
    void getStar() {
        Money totalBenefitAmount = new Money(5_000);
        assertThat(badgeGiver.getRightBadge(totalBenefitAmount))
                .isEqualTo(Badge.STAR);
    }

    @Test
    @DisplayName("총혜택 금액이 5_000원 미만이면 null을 반환한다. 줄 수 있는 게 딱히 없지 않은가")
    void getNull() {
        Money totalBenefitAmount = new Money(4_900);
        assertThat(badgeGiver.getRightBadge(totalBenefitAmount))
                .isNull();
    }
}