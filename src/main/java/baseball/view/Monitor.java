package baseball.view;

import baseball.model.baseball.BaseballCounter;

public class Monitor {
    private static final String RESULT_STRIKE_AND_BALL_MESSAGE = "%d 스트라이크 %d 볼";
    private static final String RESULT_BALL_MESSAGE = "%d 볼";
    private static final String RESULT_STRIKE_MESSAGE = "%d 스트라이크";
    private static final String RESULT_NOTHING_MESSAGE = "낫싱";
    private static final String INPUT_NUMBER_MESSAGE = "숫자를 입력해 주세요.";

    private String message;

    public void printInputNumber() {
        printMessage(INPUT_NUMBER_MESSAGE);
    }

    public void printCountResult(BaseballCounter counter) {
        printStrike(counter.isStrike(), counter.isBall(), counter.getStrike());
        printBall(counter.isStrike(), counter.isBall(), counter.getBall());
        printStrikeAndBall(counter.isStrike(), counter.isBall(), counter.getStrike(), counter.getBall());
        printNothing(counter.isStrike(), counter.isBall());
    }

    private void printStrike(boolean isStrike, boolean isBall, int numberOfStrike) {
        if (isStrike && !isBall) {
            message = String.format(RESULT_STRIKE_MESSAGE, numberOfStrike);
            printMessage(message);
        }
    }

    private void printBall(boolean isStrike, boolean isBall, int numberOfBall) {
        if (!isStrike && isBall) {
            message = String.format(RESULT_BALL_MESSAGE, numberOfBall);
            printMessage(message);
        }
    }

    private void printStrikeAndBall(boolean isStrike, boolean isBall, int numberOfStrike, int numberOfBall) {
        if (isStrike && isBall) {
            message = String.format(RESULT_STRIKE_AND_BALL_MESSAGE, numberOfStrike, numberOfBall);
            printMessage(message);
        }
    }

    private void printNothing(boolean isStrike, boolean isBall) {
        if (!isStrike && !isBall) {
            message = RESULT_NOTHING_MESSAGE;
            printMessage(message);
        }
    }

    private void printMessage(String message) {
        System.out.println(message);
    }
}
