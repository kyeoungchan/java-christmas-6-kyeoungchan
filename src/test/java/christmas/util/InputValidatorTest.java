package christmas.util;

import christmas.consts.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InputValidatorTest {
    private final InputValidator inputValidator = new InputValidator();

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
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> inputValidator.parseToValidatedInt(null)).hasMessageContaining(
                ErrorMessage.ERROR_PREFIX.getMessage(), ErrorMessage.UNVALIDATED_DATE, ErrorMessage.ERROR_POSTFIX
        );
        assertThatThrownBy(() -> inputValidator.parseToValidatedInt(""))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> inputValidator.parseToValidatedInt("")).hasMessageContaining(
                ErrorMessage.ERROR_PREFIX.getMessage(), ErrorMessage.UNVALIDATED_DATE, ErrorMessage.ERROR_POSTFIX
        );
    }

    @Test
    @DisplayName("숫자가 아닌 값을 입력하면 검증 실패")
    void notNumberInputted() {
        String notNumber = "가나다";
        assertThatThrownBy(() -> inputValidator.parseToValidatedInt(notNumber))
                .isInstanceOf(IllegalArgumentException.class);
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
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> inputValidator.parseToValidatedInt(tooBigNumber)).hasMessageContaining(
                ErrorMessage.ERROR_PREFIX.getMessage(), ErrorMessage.UNVALIDATED_DATE, ErrorMessage.ERROR_POSTFIX
        );
        assertThatThrownBy(() -> inputValidator.parseToValidatedInt(tooSmallNumber))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> inputValidator.parseToValidatedInt(tooSmallNumber)).hasMessageContaining(
                ErrorMessage.ERROR_PREFIX.getMessage(), ErrorMessage.UNVALIDATED_DATE, ErrorMessage.ERROR_POSTFIX
        );
    }
}