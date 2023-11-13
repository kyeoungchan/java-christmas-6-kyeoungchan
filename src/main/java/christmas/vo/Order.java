package christmas.vo;

import christmas.consts.Menu;

import java.util.List;

public record Order(Day visitingDay, List<Menu> menues) {
}
