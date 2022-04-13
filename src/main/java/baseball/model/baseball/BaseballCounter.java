package baseball.model.baseball;

public class BaseballCounter {
    private final String computerNumber;
    private int strike;
    private int ball;

    private BaseballCounter(String computerNumber) {
        this.computerNumber = computerNumber;
    }

    public static BaseballCounter create(String computerNumber) {
        return new BaseballCounter(computerNumber);
    }

    public void count(String userNumber) {
        countStrike(userNumber);
        countBall(userNumber);
    }

    public int getStrike() {
        return strike;
    }

    public int getBall() {
        return ball;
    }

    private void countStrike(String userNumber) {
        for (int idx = 0; idx < userNumber.length(); idx++) {
            countStrikeNumberIndex(idx, userNumber.charAt(idx));
        }
    }

    private void countStrikeNumberIndex(int index, char digitNumber) {
        if (computerNumber.charAt(index) == digitNumber) {
            strike += 1;
        }
    }

    private void countBall(String userNumber) {
        for (int idx = 0; idx < userNumber.length(); idx++) {
            countBallNumberIndex(idx, userNumber.charAt(idx));
        }
    }

    private void countBallNumberIndex(int index, char digitNumber) {
        if (computerNumber.contains(String.valueOf(digitNumber)) && computerNumber.charAt(index) != digitNumber) {
            ball += 1;
        }
    }
}
