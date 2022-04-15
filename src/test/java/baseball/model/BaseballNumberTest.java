package baseball.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import baseball.constants.baseball.BaseballOption;
import baseball.model.baseball.BaseballNumber;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

class BaseballNumberTest {
    private final static int REPEAT_NUMBER = 100;

    @Test
    void 숫자를_생성() {
        BaseballNumber number = BaseballNumber.create();

        assertAll(
                () -> assertThat(number).isNotNull(),
                () -> assertThat(number.getNumber()).isNotEmpty()
        );
    }

    @RepeatedTest(value = REPEAT_NUMBER, name = "숫자 여부 확인 {currentRepetition} / {totalRepetitions}")
    void 숫자_여부_확인() {
        BaseballNumber number = BaseballNumber.create();

        assertThat(number.getNumber()).containsOnlyDigits();
    }

    @RepeatedTest(value = REPEAT_NUMBER, name = "자릿수 일치 확인 {currentRepetition} / {totalRepetitions}")
    void 자릿수_일치_확인() {
        BaseballNumber number = BaseballNumber.create();

        assertThat(number.getNumber()).containsPattern("\\d{" + BaseballOption.DIGIT_NUMBER_LENGTH + "}");
    }

    @RepeatedTest(value = REPEAT_NUMBER, name = "숫자를 생성 {currentRepetition} / {totalRepetitions}")
    void 숫자가_양수인지_확인() {
        BaseballNumber number = BaseballNumber.create();
        int intNumber = Integer.parseInt(number.getNumber());

        assertThat(intNumber).isPositive();
    }

    @RepeatedTest(value = REPEAT_NUMBER, name = "중복된 숫자가 없는지 확인 {currentRepetition} / {totalRepetitions}")
    void 중복된_숫자가_없는지_확인() {
        BaseballNumber number = BaseballNumber.create();
        char[] digitNumberArray = number.getNumber().toCharArray();
        Set<Integer> numberSet = new HashSet<>();
        for (char digitNumber : digitNumberArray) {
            numberSet.add((int) digitNumber);
        }

        assertThat(numberSet).hasSize(BaseballOption.DIGIT_NUMBER_LENGTH);
    }
}
