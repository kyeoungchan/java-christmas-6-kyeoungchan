package christmas.view;

import christmas.consts.FormatForOutputView;
import christmas.consts.Menu;
import christmas.consts.Sentence;
import christmas.consts.Splitter;
import christmas.dto.Result;
import christmas.vo.Day;
import christmas.vo.Money;

import java.util.EnumMap;

public class OutputView {
    public void introduceEventPlanner() {
        System.out.println(Sentence.INTRODUCING_EVENT_PLANNER.getMessage());
    }

    public void introduceApplyingEvents(Day day) {
        System.out.println(Sentence.INTRODUCING_EVENTS.getMessage(day.getDate()));
    }

    public void printFinalResult(Result result) {
        printOrderMenus(result);
        printTotalPriceBeforeDiscount(result);
        lineSeparate();
        printPresentationCount(result);

    }

    private static void printPresentationCount(Result result) {
        EnumMap<Menu, Integer> presentationCount = result.presentationCount();
        System.out.println(Sentence.PRESENTATION_PREVIEW.getMessage());
        if (presentationCount.isEmpty()) {
            System.out.println(Sentence.NOTHING.getMessage());
            return;
        }
        presentationCount.forEach(((menu, count)
                -> System.out.println(FormatForOutputView.MENU_COUNT.getFormatStringInt(menu.getName(), count))));
    }

    private void printOrderMenus(Result result) {
        lineSeparate();
        EnumMap<Menu, Integer> orderMenus = result.orderMenus();
        System.out.println(Sentence.ORDER_MENU_PREVIEW.getMessage());
        orderMenus.forEach((menu, count)
                -> System.out.println(FormatForOutputView.MENU_COUNT.getFormatStringInt(menu.getName(), count)));
    }

    private void printTotalPriceBeforeDiscount(Result result) {
        lineSeparate();
        Money money = result.totalPriceBeforeDiscount();
        System.out.println(Sentence.TOTAL_PRICE_BEFORE_DISCOUNT_PREVIEW.getMessage());
        System.out.println(FormatForOutputView.AMOUNT.getFormatMoney(money.getAmount()));
    }

    private void lineSeparate() {
        System.out.print(Splitter.LINE_SEPARATOR.getRegex());
    }
}
