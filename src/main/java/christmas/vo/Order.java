package christmas.vo;

import christmas.consts.Menu;

import java.util.EnumMap;

public record Order(Day visitingDay, EnumMap<Menu, Integer> menues) {
}
