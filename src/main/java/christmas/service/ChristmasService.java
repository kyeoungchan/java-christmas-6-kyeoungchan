package christmas.service;

import christmas.consts.Badge;
import christmas.domain.BadgeGiver;
import christmas.domain.OrderMenus;
import christmas.dto.CalculateResult;
import christmas.dto.Result;
import christmas.vo.Day;
import christmas.vo.Money;

public class ChristmasService {
    private final BenefitCalculator benefitCalculator;
    private final BadgeGiver badgeGiver;

    public ChristmasService(BenefitCalculator benefitCalculator, BadgeGiver badgeGiver) {
        this.benefitCalculator = benefitCalculator;
        this.badgeGiver = badgeGiver;
    }

    public Result generateEventResult(Day day, OrderMenus orderMenus) {
        CalculateResult calculateResult = benefitCalculator.generateBenefitDetails(day, orderMenus);
        Money totalBenefitPrice = calculateResult.eventsResult().totalBenefitPrice();
        Badge badge = badgeGiver.getRightBadge(totalBenefitPrice);
        return assemblyResults(calculateResult, badge);
    }

    private static Result assemblyResults(CalculateResult calculateResult, Badge badge) {
        return new Result(
                calculateResult.orderMenus(),
                calculateResult.totalPriceBeforeDiscount(),
                calculateResult.eventsResult().presentationCount(),
                calculateResult.eventsResult().benefitAmounts(),
                calculateResult.eventsResult().totalBenefitPrice(),
                calculateResult.totalPriceAfterDiscount(),
                badge
        );
    }
}
