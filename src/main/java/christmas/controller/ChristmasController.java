package christmas.controller;

import christmas.exception.ValidatingLoopTemplate;
import christmas.util.InputValidator;
import christmas.view.InputView;
import christmas.view.OutputView;
import christmas.vo.Day;

public class ChristmasController {
    private final InputView inputView;
    private final OutputView outputView;
    private final ValidatingLoopTemplate template;

    public ChristmasController() {
        inputView = new InputView(new InputValidator());
        outputView = new OutputView();
        template = new ValidatingLoopTemplate();
    }

    public void run() {
        outputView.introduceEventPlanner();
        Day visitingDay = getVisitingDay();
    }

    private Day getVisitingDay() {
        return template.execute(() -> {
            int visitingDate = inputView.inputVisitingDay();
            return new Day(visitingDate);
        });
    }
}
