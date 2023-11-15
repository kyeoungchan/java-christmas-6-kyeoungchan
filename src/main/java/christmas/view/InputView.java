package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.consts.Menu;
import christmas.consts.Sentence;
import christmas.util.InputValidator;

import java.util.EnumMap;

public class InputView {
    private final InputValidator inputValidator;

    public InputView(InputValidator inputValidator) {
        this.inputValidator = inputValidator;
    }

    public int inputVisitingDay() {
        System.out.println(Sentence.ASKING_DAY_INPUT.getMessage());
        String inputtedDay = Console.readLine().trim();
        return inputValidator.parseToValidatedInt(inputtedDay);
    }

    public EnumMap<Menu, Integer> inputOrderMenus() {
        System.out.println(Sentence.ASKING_ORDER_MENUS_INPUT.getMessage());
        String inputtedOrderMenus = Console.readLine().trim();
        return inputValidator.validateMenus(inputtedOrderMenus);
    }
}
