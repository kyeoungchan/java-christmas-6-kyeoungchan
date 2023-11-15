package christmas.view;

import christmas.consts.Sentence;
import christmas.vo.Day;

public class OutputView {
    public void introduceEventPlanner() {
        System.out.println(Sentence.INTRODUCING_EVENT_PLANNER.getMessage());
    }

    public void introduceApplyingEvents(Day day) {
        System.out.println(Sentence.INTRODUCING_EVENTS.getMessage(day.getDate()));
    }
}
