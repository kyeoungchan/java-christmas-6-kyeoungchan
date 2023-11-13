package christmas.consts;

import christmas.vo.Money;

public enum Menu {
    BUTTON_MUSHROOM_SOUP(6_000),
    TAPAS(5_500),
    CAESAR_SALAD(8_000),
    T_BONE_STEAK(55_000),
    BARBCUE_RIBS(54_000),
    SEAFOOD_PASTA(35_000),
    CHRISTMAS_PASTA(25_000),
    CHOCOLATE_CAKE(15_000),
    ICE_CREAM(5_000),
    COKE_ZERO(3_000),
    RED_WINE(60_000),
    CHAMPAGNE(25_000);

    private final Money price;

    Menu(int amount) {
        this.price = new Money(amount);
    }

    public int getAmount() {
        return price.getAmount();
    }
}
