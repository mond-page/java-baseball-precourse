package baseball.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import baseball.constants.baseball.BaseballMessage;
import baseball.model.baseball.Baseball;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BaseballTest {
    private Baseball baseball;

    @BeforeEach
    void 야구게임_시작() {
        baseball = Baseball.start();
    }

    @Test
    void 야구게임이_생성되어_있다() {
        assertThat(baseball).isNotNull();
    }

    @ParameterizedTest(name = "숫자포맷이 맞지 않는 값({0})은 에러 발생")
    @ValueSource(strings = {"5197", " 232", "테스트", "902", "", "test", "-251", "+193"})
    void 숫자포맷이_올바르지_않는_경우(String invalidNumber) {
        assertThatThrownBy(() -> baseball.validate(invalidNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(BaseballMessage.INVALID_NUMBER_FORMAT);
    }

    @Test
    void 다른_자릿수에_중복된_수가_있는_경우() {
        String duplicateNumber = "155";

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> baseball.validate(duplicateNumber))
                .withMessage(BaseballMessage.DUPLICATE_DIGIT_NUMBER);
    }

    @Test
    void 입력받은_수를_계산_후_결과반환() {
        String inputNumber = "152";
        baseball.count(inputNumber);

        assertThat(baseball.getResult()).isNotNull();
    }
}
