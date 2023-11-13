package christmas.consts;

import java.util.List;

public enum MenuKind {
    APPETIZER(List.of(Menu.BUTTON_MUSHROOM_SOUP, Menu.TAPAS, Menu.CAESAR_SALAD)),
    MAIN(List.of(Menu.T_BONE_STEAK, Menu.BARBCUE_RIBS, Menu.SEAFOOD_PASTA, Menu.CHRISTMAS_PASTA)),
    DESSERT(List.of(Menu.CHOCOLATE_CAKE, Menu.ICE_CREAM)),
    BEVERAGE(List.of(Menu.COKE_ZERO, Menu.RED_WINE, Menu.CHAMPAGNE));

    private final List<Menu> menues;

    MenuKind(List<Menu> menues) {
        this.menues = menues;
    }

    public boolean isKindOf(Menu menu) {
        return menues.contains(menu);
    }
}
