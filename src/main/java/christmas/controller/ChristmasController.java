package christmas.controller;

import christmas.consts.Menu;
import christmas.domain.OrderMenus;
import christmas.dto.Result;
import christmas.exception.ValidatingLoopTemplate;
import christmas.service.ChristmasService;
import christmas.view.InputView;
import christmas.view.OutputView;
import christmas.vo.Day;

import java.util.EnumMap;

public class ChristmasController {
    private final InputView inputView;
    private final OutputView outputView;
    private final ValidatingLoopTemplate validatingTemplate;
    private final ChristmasService christmasService;

    public ChristmasController(InputView inputView, OutputView outputView,
                               ValidatingLoopTemplate validatingTemplate, ChristmasService christmasService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.validatingTemplate = validatingTemplate;
        this.christmasService = christmasService;
    }

    public void run() {
        outputView.introduceEventPlanner();
        Day visitingDay = getVisitingDay();
        OrderMenus orderMenus = getOrderMenus();
        generateResult(visitingDay, orderMenus);
    }

    private Day getVisitingDay() {
        return validatingTemplate.execute(() -> {
            int visitingDate = inputView.inputVisitingDay();
            return new Day(visitingDate);
        });
    }

    private OrderMenus getOrderMenus() {
        return validatingTemplate.execute(() -> {
            EnumMap<Menu, Integer> orderMenuData = inputView.inputOrderMenus();
            return new OrderMenus(orderMenuData);
        });
    }

    private void generateResult(Day visitingDay, OrderMenus orderMenus) {
        Result result = christmasService.generateEventResult(visitingDay, orderMenus);
        outputView.introduceApplyingEvents(visitingDay);
        outputView.printFinalResult(result);
    }
}
