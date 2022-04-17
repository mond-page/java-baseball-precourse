package baseball.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import baseball.model.baseball.BaseballCounter;
import baseball.model.baseball.BaseballNumber;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BaseballCounterTest {

    @Test
    void 카운터를_생성() {
        BaseballNumber baseballNumber = BaseballNumber.create();
        BaseballCounter counter = BaseballCounter.create(baseballNumber.getNumber());

        assertThat(counter).isNotNull();
    }

    @ParameterizedTest(name = "컴퓨터의 숫자({0})과 유저의 숫자({1}) 모두 스트라이크")
    @MethodSource("결과가_모두_스트라이크만_있는_경우_파라미터")
    void 결과가_모두_스트라이크만_있는_경우(List<Integer> computerNumber, List<Integer> userNumber, int strikeCount) {
        BaseballCounter counter = BaseballCounter.create(computerNumber);
        counter.count(userNumber);

        assertAll(
                () -> assertThat(computerNumber).isEqualTo(userNumber),
                () -> assertThat(counter.getStrike()).isEqualTo(strikeCount),
                () -> assertThat(counter.getBall()).isZero(),
                () -> assertThat(counter.isStrike()).isTrue(),
                () -> assertThat(counter.isBall()).isFalse()
        );
    }

    private static Stream<Arguments> 결과가_모두_스트라이크만_있는_경우_파라미터() {
        return Stream.of(
                Arguments.of(Arrays.asList(1, 5, 2), Arrays.asList(1, 5, 2), 3),
                Arguments.of(Arrays.asList(9, 1, 8, 4), Arrays.asList(9, 1, 8, 4), 4),
                Arguments.of(Arrays.asList(4, 9, 1), Arrays.asList(4, 9, 1), 3)
        );
    }

    @ParameterizedTest(name = "컴퓨터의 숫자({0})과 유저의 숫자({1}) 모두 볼")
    @MethodSource("결과가_모두_볼만_있는_경우_파라미터")
    void 결과가_모두_볼만_있는_경우(List<Integer> computerNumber, List<Integer> userNumber, int ballCount) {
        BaseballCounter counter = BaseballCounter.create(computerNumber);
        counter.count(userNumber);

        assertAll(
                () -> assertThat(computerNumber).isNotEqualTo(userNumber),
                () -> assertThat(counter.getBall()).isEqualTo(ballCount),
                () -> assertThat(counter.getStrike()).isZero(),
                () -> assertThat(counter.isStrike()).isFalse(),
                () -> assertThat(counter.isBall()).isTrue()
        );
    }

    private static Stream<Arguments> 결과가_모두_볼만_있는_경우_파라미터() {
        return Stream.of(
                Arguments.of(Arrays.asList(1, 5, 2), Arrays.asList(2, 1, 5), 3),
                Arguments.of(Arrays.asList(9, 1, 8, 4), Arrays.asList(1, 8, 4, 9), 4),
                Arguments.of(Arrays.asList(4, 9, 1), Arrays.asList(1, 4, 9), 3)
        );
    }

    @ParameterizedTest(name = "컴퓨터의 숫자({0})과 유저의 숫자({1}) 일부만 스트라이크")
    @MethodSource("결과가_스트라이크_일부만_있는_경우_파라미터")
    void 결과가_스트라이크_일부만_있는_경우(List<Integer> computerNumber, List<Integer> userNumber, int strikeCount) {
        BaseballCounter counter = BaseballCounter.create(computerNumber);
        counter.count(userNumber);

        assertAll(
                () -> assertThat(counter.getStrike()).isEqualTo(strikeCount),
                () -> assertThat(counter.getBall()).isZero(),
                () -> assertThat(counter.isStrike()).isTrue(),
                () -> assertThat(counter.isBall()).isFalse()
        );
    }

    private static Stream<Arguments> 결과가_스트라이크_일부만_있는_경우_파라미터() {
        return Stream.of(
                Arguments.of(Arrays.asList(1, 5, 2), Arrays.asList(1, 9, 6), 1),
                Arguments.of(Arrays.asList(9, 1, 8, 4), Arrays.asList(6, 1, 2, 7), 1),
                Arguments.of(Arrays.asList(4, 9, 1), Arrays.asList(4, 9, 8), 2)
        );
    }

    @ParameterizedTest(name = "컴퓨터의 숫자({0})과 유저의 숫자({1}) 일부만 볼")
    @MethodSource("결과가_볼_일부만_있는_경우_파라미터")
    void 결과가_볼_일부만_있는_경우(List<Integer> computerNumber, List<Integer> userNumber, int ballCount) {
        BaseballCounter counter = BaseballCounter.create(computerNumber);
        counter.count(userNumber);

        assertAll(
                () -> assertThat(counter.getBall()).isEqualTo(ballCount),
                () -> assertThat(counter.getStrike()).isZero(),
                () -> assertThat(counter.isStrike()).isFalse(),
                () -> assertThat(counter.isBall()).isTrue()
        );
    }

    private static Stream<Arguments> 결과가_볼_일부만_있는_경우_파라미터() {
        return Stream.of(
                Arguments.of(Arrays.asList(1, 5, 2), Arrays.asList(2, 1, 7), 2),
                Arguments.of(Arrays.asList(9, 1, 8, 4), Arrays.asList(1, 8, 6, 7), 2),
                Arguments.of(Arrays.asList(4, 9, 1), Arrays.asList(1, 5, 6), 1)
        );
    }

    @ParameterizedTest(name = "컴퓨터의 숫자({0})과 유저의 숫자({1}) 스트라이크, 볼")
    @MethodSource("결과가_스트라이크_볼_함께_있는_경우_파라미터")
    void 결과가_스트라이크_볼_함께_있는_경우(List<Integer> computerNumber, List<Integer> userNumber, int strikeCount, int ballCount) {
        BaseballCounter counter = BaseballCounter.create(computerNumber);
        counter.count(userNumber);

        assertAll(
                () -> assertThat(counter.getStrike()).isEqualTo(strikeCount),
                () -> assertThat(counter.getBall()).isEqualTo(ballCount),
                () -> assertThat(counter.isStrike()).isTrue(),
                () -> assertThat(counter.isBall()).isTrue()
        );
    }

    private static Stream<Arguments> 결과가_스트라이크_볼_함께_있는_경우_파라미터() {
        return Stream.of(
                Arguments.of(Arrays.asList(1, 5, 2), Arrays.asList(1, 2, 5), 1, 2),
                Arguments.of(Arrays.asList(9, 1, 8, 4), Arrays.asList(1, 7, 8, 4), 2, 1),
                Arguments.of(Arrays.asList(4, 9, 1), Arrays.asList(4, 1, 8), 1, 1)
        );
    }

    @ParameterizedTest(name = "컴퓨터의 숫자({0})과 유저의 숫자({1}) 낫싱")
    @MethodSource("결과가_낫싱인_경우_파라미터")
    void 결과가_낫싱인_경우(List<Integer> computerNumber, List<Integer> userNumber) {
        BaseballCounter counter = BaseballCounter.create(computerNumber);
        counter.count(userNumber);

        assertAll(
                () -> assertThat(counter.getStrike()).isZero(),
                () -> assertThat(counter.getBall()).isZero(),
                () -> assertThat(counter.isStrike()).isFalse(),
                () -> assertThat(counter.isBall()).isFalse()
        );
    }

    private static Stream<Arguments> 결과가_낫싱인_경우_파라미터() {
        return Stream.of(
                Arguments.of(Arrays.asList(1, 5, 2), Arrays.asList(9, 8, 7)),
                Arguments.of(Arrays.asList(9, 1, 8, 4), Arrays.asList(2, 3, 6, 5)),
                Arguments.of(Arrays.asList(4, 9, 1), Arrays.asList(2, 5, 7))
        );
    }
}
