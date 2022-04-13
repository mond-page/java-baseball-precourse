package baseball.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import baseball.model.baseball.Baseball;
import baseball.model.baseball.constants.BaseballMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BaseballTest {

    @Test
    void 야구게임_시작() {
        Baseball baseball = Baseball.start();

        assertThat(baseball).isNotNull();
    }

    @ParameterizedTest(name = "숫자포맷이 맞지 않는 값({0})은 에러 발생")
    @ValueSource(strings = {"5197", " 232", "테스트", "902", "", "test", "-251", "+193"})
    void 숫자포맷이_올바르지_않는_경우(String invalidNumber) {
        Baseball baseball = Baseball.start();

        assertThatThrownBy(() -> baseball.validate(invalidNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(BaseballMessage.INVALID_NUMBER_FORMAT);
    }

    @Test
    void 다른_자릿수에_중복된_수가_있는_경우() {
        Baseball baseball = Baseball.start();
        String duplicateNumber = "155";

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> baseball.validate(duplicateNumber))
                .withMessage(BaseballMessage.DUPLICATE_DIGIT_NUMBER);
    }
}
