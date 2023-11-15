package christmas.consts;

import christmas.vo.Money;

import java.util.Objects;

public enum Menu {
    BUTTON_MUSHROOM_SOUP(6_000, "양송이수프"),
    TAPAS(5_500, "타파스"),
    CAESAR_SALAD(8_000, "시저샐러드"),
    T_BONE_STEAK(55_000, "티본스테이크"),
    BARBCUE_RIBS(54_000,"바비큐립"),
    SEAFOOD_PASTA(35_000,"해산물파스타"),
    CHRISTMAS_PASTA(25_000,"크리스마스파스타"),
    CHOCOLATE_CAKE(15_000,"초코케이크"),
    ICE_CREAM(5_000,"아이스크림"),
    COKE_ZERO(3_000,"제로콜라"),
    RED_WINE(60_000,"레드와인"),
    CHAMPAGNE(25_000,"샴페인");

    private final Money price;
    private final String name;

    Menu(int amount, String name) {
        this.price = new Money(amount);
        this.name = name;
    }

    public int getAmount() {
        return price.amount();
    }

    public boolean isThisMenu(String name) {
        return Objects.equals(this.name, name);
    }

    public String getName() {
        return name;
    }
}
