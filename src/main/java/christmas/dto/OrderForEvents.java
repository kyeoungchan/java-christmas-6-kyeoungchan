package christmas.dto;

import christmas.consts.Menu;
import christmas.vo.Day;
import christmas.vo.Money;

import java.util.EnumMap;

public record OrderForEvents(Day visitingDay, EnumMap<Menu, Integer> menuCount, Money totalOrderPrice) {
}
