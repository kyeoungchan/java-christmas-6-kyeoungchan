package christmas.view;

import christmas.consts.Sentence;
import christmas.consts.Menu;
import christmas.consts.FormatForOutputView;
import christmas.consts.Event;
import christmas.consts.Splitter;
import christmas.consts.Badge;
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
        printPresentationCount(result);
        printBenefitAmountsPerEvent(result);
        printTotalBenefitPrice(result);
        printTotalPriceAfterDiscount(result);
        printBadge(result);
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

    private void printPresentationCount(Result result) {
        lineSeparate();
        EnumMap<Menu, Integer> presentationCount = result.presentationCount();
        System.out.println(Sentence.PRESENTATION_PREVIEW.getMessage());
        if (presentationCount.isEmpty()) {
            System.out.println(Sentence.NOTHING.getMessage());
            return;
        }
        presentationCount.forEach(((menu, count)
                -> System.out.println(FormatForOutputView.MENU_COUNT.getFormatStringInt(menu.getName(), count))));
    }

    private void printBenefitAmountsPerEvent(Result result) {
        lineSeparate();
        EnumMap<Event, Money> benefitAmountsPerEvent = result.benefitAmountsPerEvent();
        System.out.println(Sentence.BENEFIT_DETAILS_PREVIEW.getMessage());
        if (benefitAmountsPerEvent.isEmpty()) {
            System.out.println(Sentence.NOTHING.getMessage());
            return;
        }
        benefitAmountsPerEvent.forEach(((event, money)
                -> System.out.println(FormatForOutputView.EVENT_BENEFIT_AMOUNT
                .getFormatStringInt(event.getName(), money.getAmount())))
        );
    }

    private void printTotalBenefitPrice(Result result) {
        lineSeparate();
        Money totalBenefitPrice = result.totalBenefitPrice();
        System.out.println(Sentence.TOTAL_BENEFIT_AMOUNT_PREVIEW.getMessage());
        System.out.println(FormatForOutputView.AMOUNT.getFormatMoney(totalBenefitPrice.getAmount()));
    }

    private void printTotalPriceAfterDiscount(Result result) {
        lineSeparate();
        Money totalPriceAfterDiscount = result.totalPriceAfterDiscount();
        System.out.println(Sentence.TOTAL_PRICE_AFTER_DISCOUNT_PREVIEW.getMessage());
        System.out.println(FormatForOutputView.AMOUNT.getFormatMoney(totalPriceAfterDiscount.getAmount()));
    }

    private void printBadge(Result result) {
        lineSeparate();
        Badge badge = result.badge();
        System.out.println(Sentence.GIVING_BADGE_PREVIEW.getMessage());
        if (badge == null) {
            System.out.println(Sentence.NOTHING.getMessage());
            return;
        }
        System.out.println(badge);
    }

    private void lineSeparate() {
        System.out.print(Splitter.LINE_SEPARATOR.getRegex());
    }
}
