package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.consts.Sentence;
import christmas.util.InputValidator;

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
}
