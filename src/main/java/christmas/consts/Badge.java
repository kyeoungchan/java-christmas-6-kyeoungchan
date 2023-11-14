package christmas.consts;

import christmas.vo.Money;

public enum Badge {
    STAR(5_000),
    TREE(10_000),
    SANTA(20_000);

    private final int standardAmount;

    Badge(int standardAmount) {
        this.standardAmount = standardAmount;
    }

    public int getStandardAmount() {
        return standardAmount;
    }

    public boolean deserveThisBadge(Money totalBenefitPrice) {
        return Math.abs(totalBenefitPrice.getAmount()) >= standardAmount;
    }
}
