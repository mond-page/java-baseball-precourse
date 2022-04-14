package baseball.view;

import baseball.model.baseball.BaseballCounter;

public class Monitor {
    private static final String RESULT_STRIKE_AND_BALL_MESSAGE = "%d 스트라이크 %d 볼";
    private static final String RESULT_BALL_MESSAGE = "%d 볼";
    private static final String RESULT_STRIKE_MESSAGE = "%d 스트라이크";
    private static final String RESULT_NOTHING_MESSAGE = "낫싱";
    private static final String INPUT_NUMBER_MESSAGE = "숫자를 입력해 주세요.";

    public void printInputNumber() {
        System.out.println(INPUT_NUMBER_MESSAGE);
    }

    public void printCountResult(BaseballCounter counter) {
        printStrike(counter.isStrike(), counter.isBall(), counter.getStrike());
        printBall(counter.isStrike(), counter.isBall(), counter.getBall());
        printStrikeAndBall(counter.isStrike(), counter.isBall(), counter.getStrike(), counter.getBall());
        printNothing(counter.isStrike(), counter.isBall());
    }

    private void printNothing(boolean isStrike, boolean isBall) {
        if (!isStrike && !isBall) {
            System.out.println(RESULT_NOTHING_MESSAGE);
        }
    }

    private void printBall(boolean isStrike, boolean isBall, int numberOfBall) {
        if (!isStrike && isBall) {
            System.out.printf(RESULT_BALL_MESSAGE, numberOfBall);
        }
    }

    private void printStrike(boolean isStrike, boolean isBall, int numberOfStrike) {
        if (isStrike && !isBall) {
            System.out.printf(RESULT_STRIKE_MESSAGE, numberOfStrike);
        }
    }

    private void printStrikeAndBall(boolean isStrike, boolean isBall, int numberOfStrike, int numberOfBall) {
        if (isStrike && isBall) {
            System.out.printf(RESULT_STRIKE_AND_BALL_MESSAGE, numberOfStrike, numberOfBall);
        }
    }
}
