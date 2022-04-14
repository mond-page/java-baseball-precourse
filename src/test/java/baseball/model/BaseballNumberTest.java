package baseball.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import baseball.model.baseball.BaseballNumber;
import baseball.constants.baseball.BaseballOption;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class BaseballNumberTest {

    @Test
    void 숫자를_생성() {
        BaseballNumber number = BaseballNumber.create();

        assertAll(
                () -> assertThat(number).isNotNull(),
                () -> assertThat(number.getNumber()).isNotEmpty()
        );
    }

    @Test
    void 숫자_여부_확인() {
        BaseballNumber number = BaseballNumber.create();

        assertThat(number.getNumber()).containsOnlyDigits();
    }

    @Test
    void 자릿수_일치_확인() {
        BaseballNumber number = BaseballNumber.create();

        assertThat(number.getNumber()).containsPattern("\\d{" + BaseballOption.DIGIT_NUMBER_LENGTH + "}");
    }

    @Test
    void 숫자가_양수인지_확인() {
        BaseballNumber number = BaseballNumber.create();
        int intNumber = Integer.parseInt(number.getNumber());

        assertThat(intNumber).isPositive();
    }

    @Test
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
