package christmas.domain;

import christmas.consts.ErrorMessage;
import christmas.consts.Menu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MenuOrderAssemblerTest {
    private final MenuOrderAssembler menuOrderAssembler = new MenuOrderAssembler();

    @Test
    @DisplayName("입력받은 메뉴 이름과 개수에 대한 정보가 문제 없으면 EnumMap 타입으로 전달한다.")
    void generateMenuCounts() {
        List<String> menuNames = List.of("양송이수프", "타파스");
        List<Integer> counts = List.of(1, 2);
        assertThatNoException().isThrownBy(() -> menuOrderAssembler.generateMenuCounts(menuNames, counts));
        assertThat(menuOrderAssembler.generateMenuCounts(menuNames, counts).keySet())
                .containsExactly(Menu.BUTTON_MUSHROOM_SOUP, Menu.TAPAS);
        assertThat(menuOrderAssembler.generateMenuCounts(menuNames, counts).values())
                .containsExactly(1, 2);
    }

    @Test
    @DisplayName("입력받은 메뉴 이름 리스트와 개수 리스트의 크기가 다르면 내부 에러 발생")
    void notMatchLists() {
        List<String> menuNames = List.of("양송이수프", "타파스");
        List<Integer> counts = List.of(1);
        assertThatThrownBy(() -> menuOrderAssembler.generateMenuCounts(menuNames, counts))
                .isExactlyInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> menuOrderAssembler.generateMenuCounts(menuNames, counts))
                .hasMessageContaining(ErrorMessage.INTERNAL.getMessage());
    }

    @Test
    @DisplayName("입력받은 메뉴 이름이 메뉴에 해당되지 않으면 예외 발생")
    void notMenuName() {
        List<String> menuNames = List.of("이야앙송이수프", "트아아파스");
        List<Integer> counts = List.of(1, 2);
        assertThatThrownBy(() -> menuOrderAssembler.generateMenuCounts(menuNames, counts))
                .isExactlyInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> menuOrderAssembler.generateMenuCounts(menuNames, counts))
                .hasMessageContaining(ErrorMessage.UNVALIDATED_ORDER.getMessage());
    }

    @Test
    @DisplayName("입력받은 메뉴 이름이 중복되면 예외 발생")
    void duplicatedMenuNames() {
        List<String> menuNames = List.of("타파스", "타파스");
        List<Integer> counts = List.of(1, 2);
        assertThatThrownBy(() -> menuOrderAssembler.generateMenuCounts(menuNames, counts))
                .isExactlyInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> menuOrderAssembler.generateMenuCounts(menuNames, counts))
                .hasMessageContaining(ErrorMessage.UNVALIDATED_ORDER.getMessage());
    }

    @Test
    @DisplayName("당연히 메뉴 갯수는 중복돼도 상관없다.")
    void duplicatedCounts() {
        List<String> menuNames = List.of("양송이수프", "타파스");
        List<Integer> counts = List.of(1, 1);
        assertThatNoException().isThrownBy(() -> menuOrderAssembler.generateMenuCounts(menuNames, counts));
        assertThat(menuOrderAssembler.generateMenuCounts(menuNames, counts).keySet())
                .containsExactly(Menu.BUTTON_MUSHROOM_SOUP, Menu.TAPAS);
        assertThat(menuOrderAssembler.generateMenuCounts(menuNames, counts).values())
                .containsExactly(1, 1);
    }

}