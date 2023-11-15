package christmas.util;

import christmas.consts.ErrorMessage;
import christmas.consts.Menu;
import christmas.domain.MenuOrderAssembler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

class InputValidatorTest {
    private final InputValidator inputValidator = new InputValidator(new MenuOrderAssembler());

    @Test
    @DisplayName("100_000 이하의 숫자를 입력한다면 검증 성공")
    void parseToValidatedInt() {
        for (int i = 0; i < 100; i++) {
            String positiveNumber = String.valueOf(i);
            String negativeNumber = String.valueOf(-i);
            assertThatNoException().isThrownBy(() -> inputValidator.parseToValidatedInt(positiveNumber));
            assertThatNoException().isThrownBy(() -> inputValidator.parseToValidatedInt(negativeNumber));
        }
    }

    @Test
    @DisplayName("null이나 공백을 입력하면 검증 실패")
    void emptyInput() {
        assertThatThrownBy(() -> inputValidator.parseToValidatedInt(null))
                .isExactlyInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> inputValidator.parseToValidatedInt(null)).hasMessageContaining(
                ErrorMessage.ERROR_PREFIX.getMessage(), ErrorMessage.UNVALIDATED_DATE, ErrorMessage.ERROR_POSTFIX
        );
        assertThatThrownBy(() -> inputValidator.parseToValidatedInt(""))
                .isExactlyInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> inputValidator.parseToValidatedInt("")).hasMessageContaining(
                ErrorMessage.ERROR_PREFIX.getMessage(), ErrorMessage.UNVALIDATED_DATE, ErrorMessage.ERROR_POSTFIX
        );
    }

    @Test
    @DisplayName("숫자가 아닌 값을 입력하면 검증 실패")
    void notNumberInputted() {
        String notNumber = "가나다";
        assertThatThrownBy(() -> inputValidator.parseToValidatedInt(notNumber))
                .isExactlyInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> inputValidator.parseToValidatedInt(notNumber)).hasMessageContaining(
                ErrorMessage.ERROR_PREFIX.getMessage(), ErrorMessage.UNVALIDATED_DATE, ErrorMessage.ERROR_POSTFIX
        );
    }

    @Test
    @DisplayName("절대값이 100_000 이상의 숫자를 입력하면 검증 실패")
    void unsafeMemoryInputted() {
        String tooBigNumber = "100001";
        String tooSmallNumber = "-100001";
        assertThatThrownBy(() -> inputValidator.parseToValidatedInt(tooBigNumber))
                .isExactlyInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> inputValidator.parseToValidatedInt(tooBigNumber)).hasMessageContaining(
                ErrorMessage.ERROR_PREFIX.getMessage(), ErrorMessage.UNVALIDATED_DATE, ErrorMessage.ERROR_POSTFIX
        );
        assertThatThrownBy(() -> inputValidator.parseToValidatedInt(tooSmallNumber))
                .isExactlyInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> inputValidator.parseToValidatedInt(tooSmallNumber)).hasMessageContaining(
                ErrorMessage.ERROR_PREFIX.getMessage(), ErrorMessage.UNVALIDATED_DATE, ErrorMessage.ERROR_POSTFIX
        );
    }

    @Test
    @DisplayName("형식에 맞춰 입력하면 메뉴와 갯수를 EnumMap에 넣어서 반환")
    void validateMenus() {
        String validatedInput = "해산물파스타-2,레드와인-1,초코케이크-1";
        EnumMap<Menu, Integer> result = inputValidator.validateMenus(validatedInput);
        assertThat(result.get(Menu.SEAFOOD_PASTA)).isEqualTo(2);
        assertThat(result.get(Menu.RED_WINE)).isEqualTo(1);
        assertThat(result.get(Menu.CHOCOLATE_CAKE)).isEqualTo(1);
    }

    @Test
    @DisplayName("쉼표 사이에 공백이면 검증 실패")
    void inputBlankBetweenComma() {
        String unvalidatedInput = "해산물파스타-2,,레드와인-1";
        assertThatThrownBy(() -> inputValidator.validateMenus(unvalidatedInput))
                .isExactlyInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> inputValidator.parseToValidatedInt(unvalidatedInput)).hasMessageContaining(
                ErrorMessage.ERROR_PREFIX.getMessage(), ErrorMessage.UNVALIDATED_ORDER, ErrorMessage.ERROR_POSTFIX
        );
    }

    @Test
    @DisplayName("쉼표 전후로 공백은 자동으로 제거")
    void inputBlankWithValidatedData() {
        String validatedInput = "  해산물파스타-2   ,        레드와인-1    ,     초코케이크-1  ";
        assertThatNoException().isThrownBy(() -> inputValidator.validateMenus(validatedInput));
    }

    @Test
    @DisplayName("-바로 문자열을 나눴을 때 갯수가 0개면 검증 실패")
    void inputZeroSepartedByBar() {
        String unvalidatedInput = "해산물파스타-2,-,초코케이크-1";
        assertThatThrownBy(() -> inputValidator.validateMenus(unvalidatedInput))
                .isExactlyInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> inputValidator.parseToValidatedInt(unvalidatedInput)).hasMessageContaining(
                ErrorMessage.ERROR_PREFIX.getMessage(), ErrorMessage.UNVALIDATED_ORDER, ErrorMessage.ERROR_POSTFIX
        );
    }

    @Test
    @DisplayName("-바로 문자열을 나눴을 때 갯수가 1개면 검증 실패")
    void inputOneSepartedByBar() {
        String unvalidatedInput = "해산물파스타-2,레드와인-1,초코케이크-";
        assertThatThrownBy(() -> inputValidator.validateMenus(unvalidatedInput))
                .isExactlyInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> inputValidator.parseToValidatedInt(unvalidatedInput)).hasMessageContaining(
                ErrorMessage.ERROR_PREFIX.getMessage(), ErrorMessage.UNVALIDATED_ORDER, ErrorMessage.ERROR_POSTFIX
        );
    }

    @Test
    @DisplayName("-바로 문자열을 나눴을 때 갯수가 3개면 검증 실패")
    void inputThreeSepartedByBar() {
        String unvalidatedInput = "해산물파스타-2,레드와인-1-아이스크림,초코케이크-1";
        assertThatThrownBy(() -> inputValidator.validateMenus(unvalidatedInput))
                .isExactlyInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> inputValidator.parseToValidatedInt(unvalidatedInput)).hasMessageContaining(
                ErrorMessage.ERROR_PREFIX.getMessage(), ErrorMessage.UNVALIDATED_ORDER, ErrorMessage.ERROR_POSTFIX
        );
    }

    @Test
    @DisplayName("개수 자리에 숫자가 아닌 것 입력하면 검증 실패")
    void inputNotNumberCount() {
        String unvalidatedInput = "해산물파스타-2,레드와인-아이스크림,초코케이크-2";
        assertThatThrownBy(() -> inputValidator.validateMenus(unvalidatedInput))
                .isExactlyInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> inputValidator.parseToValidatedInt(unvalidatedInput)).hasMessageContaining(
                ErrorMessage.ERROR_PREFIX.getMessage(), ErrorMessage.UNVALIDATED_ORDER, ErrorMessage.ERROR_POSTFIX
        );
    }
}