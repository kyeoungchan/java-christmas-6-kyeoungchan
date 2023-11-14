package christmas.consts;

import christmas.domain.*;

public enum Event {
    CHRISTMAS_D_DAY_EVENT("크리스마스 디데이 할인") {
        @Override
        public EventCalculatorAdapter getEventCalculator() {
            return new ChristmasDayCalculator();
        }
    },
    WEEKDAY_EVENT("평일 할인") {
        @Override
        public EventCalculatorAdapter getEventCalculator() {
            return new WeekdayCalculator();
        }
    },
    WEEKEND_EVENT("주말 할인") {
        @Override
        public EventCalculatorAdapter getEventCalculator() {
            return new WeekendCalculator();
        }
    },
    SPECIAL_EVENT("특별 할인") {
        @Override
        public EventCalculatorAdapter getEventCalculator() {
            return new SpecialCalculator();
        }
    },
    PRESENTATION_EVENT("증정 이벤트") {
        @Override
        public EventCalculatorAdapter getEventCalculator() {
            return new PresentationCalculator();
        }
    };

    private final String name;

    Event(String name) {
        this.name = name;
    }

    public abstract EventCalculatorAdapter getEventCalculator();
}
