package baseball.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import baseball.constants.baseball.BaseballOption;
import baseball.model.baseball.BaseballNumber;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

class BaseballNumberTest {
    private final static int REPEAT_NUMBER = 100;
    private BaseballNumber baseballNumber;

    @BeforeEach
    void 숫자_생성() {
        baseballNumber = BaseballNumber.create();
    }

    @Test
    void 숫자_생성_확인() {
        assertAll(
                () -> assertThat(baseballNumber).isNotNull(),
                () -> assertThat(baseballNumber.getNumber()).isNotEmpty()
        );
    }

    @RepeatedTest(value = REPEAT_NUMBER, name = "숫자 정상범위 여부 확인 {currentRepetition} / {totalRepetitions}")
    void 숫자_정상범위_여부_확인() {
        assertAll(
                () -> 리스트_정상범위_여부_확인(baseballNumber.getNumber()),
                () -> assertThat(baseballNumber.getNumber()).doesNotContain(0)
        );
    }

    void 리스트_정상범위_여부_확인(List<Integer> number) {
        for (Integer digitNumber : number) {
            assertThat(digitNumber).isBetween(BaseballOption.MIN_NUMBER, BaseballOption.MAX_NUMBER);
        }
    }

    @RepeatedTest(value = REPEAT_NUMBER, name = "자릿수 일치 확인 {currentRepetition} / {totalRepetitions}")
    void 자릿수_일치_확인() {
        assertThat(baseballNumber.getNumber()).hasSize(BaseballOption.DIGIT_NUMBER_LENGTH);
    }

    @RepeatedTest(value = REPEAT_NUMBER, name = "중복된 숫자가 없는지 확인 {currentRepetition} / {totalRepetitions}")
    void 중복된_숫자가_없는지_확인() {
        Set<Integer> numberSet = new HashSet<>(baseballNumber.getNumber());

        assertThat(numberSet).hasSize(BaseballOption.DIGIT_NUMBER_LENGTH);
    }
}
