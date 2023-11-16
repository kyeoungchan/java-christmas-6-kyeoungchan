package christmas;

import christmas.controller.ChristmasController;

public class Application {
    public static void main(String[] args) {
        ChristmasController christmasController = Configuration.getChristmasController();
        christmasController.run();
    }
}
