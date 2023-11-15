package christmas.consts;

import christmas.vo.Money;

public enum Badge {
    STAR(5_000, "별"),
    TREE(10_000, "트리"),
    SANTA(20_000,"산타");

    private final int standardAmount;
    private final String name;

    Badge(int standardAmount, String name) {
        this.standardAmount = standardAmount;
        this.name = name;
    }

    public int getStandardAmount() {
        return standardAmount;
    }

    public boolean deserveThisBadge(Money totalBenefitPrice) {
        return Math.abs(totalBenefitPrice.getAmount()) >= standardAmount;
    }

    @Override
    public String toString() {
        return name;
    }
}
