package christmas.view;

import christmas.consts.FormatForOutputView;
import christmas.consts.Menu;
import christmas.consts.Sentence;
import christmas.consts.Splitter;
import christmas.dto.Result;
import christmas.vo.Day;

import java.util.EnumMap;

public class OutputView {
    public void introduceEventPlanner() {
        System.out.println(Sentence.INTRODUCING_EVENT_PLANNER.getMessage());
    }

    public void introduceApplyingEvents(Day day) {
        System.out.println(Sentence.INTRODUCING_EVENTS.getMessage(day.getDate()));
    }

    public void printFinalResult(Result result) {
        EnumMap<Menu, Integer> orderMenus = result.orderMenus();
        lineSeparate();
        System.out.println(Sentence.ORDER_MENU_PREVIEW.getMessage());
        orderMenus.forEach((menu, count)
                -> System.out.println(FormatForOutputView.ORDER_MENU.getFormatStringInt(menu.getName(), count)));

    }

    private void lineSeparate() {
        System.out.print(Splitter.LINE_SEPARATOR.getRegex());
    }
}
