package christmas.domain;

import christmas.consts.Badge;
import christmas.vo.Money;

import java.util.Arrays;

public class BadgeGiver {
    public Badge getRightBadge(Money totalBenefitAmount) {
        return Arrays.stream(Badge.values())
                .sorted((x, y) -> y.getStandardAmount() - x.getStandardAmount())
                .filter(b -> b.deserveThisBadge(totalBenefitAmount))
                .findFirst().orElse(null);
    }
}
