package baseball.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import baseball.model.baseball.BaseballCounter;
import baseball.model.baseball.BaseballNumber;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BaseballCounterTest {

    @Test
    void 카운터를_생성() {
        BaseballNumber baseballNumber = BaseballNumber.create();
        BaseballCounter counter = BaseballCounter.create(baseballNumber.getNumber());

        assertThat(counter).isNotNull();
    }

    @ParameterizedTest(name = "컴퓨터의 숫자({0})과 유저의 숫자({1}) 모두 스트라이크")
    @CsvSource(value = {"152:152:3", "9184:9184:4", "491:491:3"}, delimiter = ':')
    void 결과가_모두_스트라이크만_있는_경우(String computerNumber, String userNumber, int strikeCount) {
        BaseballCounter counter = BaseballCounter.create(computerNumber);
        counter.count(userNumber);

        assertAll(
                () -> 두_배열_스트라이크_만장일치(computerNumber.toCharArray(), userNumber.toCharArray()),
                () -> assertThat(counter.getStrike()).isEqualTo(strikeCount),
                () -> assertThat(counter.getBall()).isZero(),
                () -> assertThat(counter.isStrike()).isTrue(),
                () -> assertThat(counter.isBall()).isFalse()
        );
    }

    private void 두_배열_스트라이크_만장일치(char[] computerNumberArray, char[] userNumberArray) {
        assertArrayEquals(computerNumberArray, userNumberArray);
    }

    @ParameterizedTest(name = "컴퓨터의 숫자({0})과 유저의 숫자({1}) 모두 볼")
    @CsvSource(value = {"152:215:3", "9184:1849:4", "491:149:3"}, delimiter = ':')
    void 결과가_모두_볼만_있는_경우(String computerNumber, String userNumber, int ballCount) {
        BaseballCounter counter = BaseballCounter.create(computerNumber);
        counter.count(userNumber);

        assertAll(
                () -> 두_배열_볼_만장일치(computerNumber.toCharArray(), userNumber.toCharArray()),
                () -> assertThat(counter.getBall()).isEqualTo(ballCount),
                () -> assertThat(counter.getStrike()).isZero(),
                () -> assertThat(counter.isStrike()).isFalse(),
                () -> assertThat(counter.isBall()).isTrue()
        );
    }

    private void 두_배열_볼_만장일치(char[] computerNumberArray, char[] userNumberArray) {
        for (int idx = 0; idx < computerNumberArray.length; idx++) {
            int innerIdx = idx;

            assertAll(
                    () -> assertThat(computerNumberArray[innerIdx]).isNotEqualTo(userNumberArray[innerIdx]),
                    () -> assertThat(computerNumberArray).contains(userNumberArray[innerIdx])
            );
        }
    }

    @ParameterizedTest(name = "컴퓨터의 숫자({0})과 유저의 숫자({1}) 일부만 스트라이크")
    @CsvSource(value = {"152:196:1", "9184:6127:1", "491:498:2"}, delimiter = ':')
    void 결과가_스트라이크가_일부만_있는_경우(String computerNumber, String userNumber, int strikeCount) {
        BaseballCounter counter = BaseballCounter.create(computerNumber);
        counter.count(userNumber);

        assertAll(
                () -> assertThat(counter.getStrike()).isEqualTo(strikeCount),
                () -> assertThat(counter.getBall()).isZero(),
                () -> assertThat(counter.isStrike()).isTrue(),
                () -> assertThat(counter.isBall()).isFalse()
        );
    }

    @ParameterizedTest(name = "컴퓨터의 숫자({0})과 유저의 숫자({1}) 일부만 볼")
    @CsvSource(value = {"152:217:2", "9184:1867:2", "491:156:1"}, delimiter = ':')
    void 결과가_볼이_일부만_있는_경우(String computerNumber, String userNumber, int ballCount) {
        BaseballCounter counter = BaseballCounter.create(computerNumber);
        counter.count(userNumber);

        assertAll(
                () -> assertThat(counter.getBall()).isEqualTo(ballCount),
                () -> assertThat(counter.getStrike()).isZero(),
                () -> assertThat(counter.isStrike()).isFalse(),
                () -> assertThat(counter.isBall()).isTrue()
        );
    }

    @ParameterizedTest(name = "컴퓨터의 숫자({0})과 유저의 숫자({1}) 스트라이크, 볼")
    @CsvSource(value = {"152:125:1:2", "9184:1784:2:1", "491:418:1:1"}, delimiter = ':')
    void 결과가_스트라이크_볼_함께_있는_경우(String computerNumber, String userNumber, int strikeCount, int ballCount) {
        BaseballCounter counter = BaseballCounter.create(computerNumber);
        counter.count(userNumber);

        assertAll(
                () -> assertThat(counter.getStrike()).isEqualTo(strikeCount),
                () -> assertThat(counter.getBall()).isEqualTo(ballCount),
                () -> assertThat(counter.isStrike()).isTrue(),
                () -> assertThat(counter.isBall()).isTrue()
        );
    }

    @ParameterizedTest(name = "컴퓨터의 숫자({0})과 유저의 숫자({1}) 낫싱")
    @CsvSource(value = {"152:987", "9184:2365", "491:257"}, delimiter = ':')
    void 결과가_낫싱일_경우(String computerNumber, String userNumber) {
        BaseballCounter counter = BaseballCounter.create(computerNumber);
        counter.count(userNumber);

        assertAll(
                () -> assertThat(counter.getStrike()).isZero(),
                () -> assertThat(counter.getBall()).isZero(),
                () -> assertThat(counter.isStrike()).isFalse(),
                () -> assertThat(counter.isBall()).isFalse()
        );
    }

}
