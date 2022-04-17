package baseball.model.baseball;

import baseball.constants.baseball.BaseballOption;
import java.util.List;

public class BaseballCounter {
    private final List<Integer> computerNumber;
    private int strike;
    private int ball;

    private BaseballCounter(List<Integer> computerNumber) {
        this.computerNumber = computerNumber;
    }

    public static BaseballCounter create(List<Integer> computerNumber) {
        return new BaseballCounter(computerNumber);
    }

    public void count(List<Integer> userNumber) {
        countStrike(userNumber);
        countBall(userNumber);
    }

    private void countStrike(List<Integer> userNumber) {
        for (int idx = 0; idx < userNumber.size(); idx++) {
            countStrikeNumberIndex(idx, userNumber.get(idx));
        }
    }

    private void countStrikeNumberIndex(int index, int digitNumber) {
        if (computerNumber.get(index) == digitNumber) {
            strike += 1;
        }
    }

    private void countBall(List<Integer> userNumber) {
        for (int idx = 0; idx < userNumber.size(); idx++) {
            countBallNumberIndex(idx, userNumber.get(idx));
        }
    }

    private void countBallNumberIndex(int index, int digitNumber) {
        if (computerNumber.contains(digitNumber) && computerNumber.get(index) != digitNumber) {
            ball += 1;
        }
    }

    public int getStrike() {
        return strike;
    }

    public int getBall() {
        return ball;
    }

    public boolean isStrike() {
        return strike > 0;
    }

    public boolean isBall() {
        return ball > 0;
    }

    public boolean isAllStrike() {
        return strike == BaseballOption.DIGIT_NUMBER_LENGTH;
    }
}
