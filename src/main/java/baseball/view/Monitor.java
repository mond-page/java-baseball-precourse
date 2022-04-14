package baseball.view;

import baseball.constants.baseball.BaseballOption;
import baseball.model.baseball.BaseballCounter;
import baseball.constants.gamemachine.GameMachineState;

public class Monitor {
    private static final String RESULT_BALL_AND_STRIKE_MESSAGE = "%d볼 %d스트라이크";
    private static final String RESULT_BALL_MESSAGE = "%d볼";
    private static final String RESULT_STRIKE_MESSAGE = "%d스트라이크";
    private static final String RESULT_NOTHING_MESSAGE = "낫싱";
    private static final String INPUT_NUMBER_MESSAGE = "숫자를 입력해 주세요.";
    private static final String GAME_VICTORY_MESSAGE = "%d개의 숫자를 모두 맞히셨습니다! 게임 종료";
    private static final String GAME_CHOICE_MESSAGE = "게임을 새로 시작하려면 %d, 종료하려면 %d를 입력하세요.";
    private static final String GAME_END_MESSAGE = "게임 종료";

    private String message;

    public void printInputNumber() {
        printMessage(INPUT_NUMBER_MESSAGE);
    }

    public void printCountResult(BaseballCounter counter) {
        printStrike(counter.isStrike(), counter.isBall(), counter.getStrike());
        printBall(counter.isStrike(), counter.isBall(), counter.getBall());
        printBallAndStrike(counter.isStrike(), counter.isBall(), counter.getStrike(), counter.getBall());
        printNothing(counter.isStrike(), counter.isBall());
    }

    public void printVictory() {
        message = String.format(GAME_VICTORY_MESSAGE, BaseballOption.DIGIT_NUMBER);
        printMessage(message);
    }

    public void printGameChoice() {
        message = String.format(GAME_CHOICE_MESSAGE, GameMachineState.RESTART.getCode(), GameMachineState.TERMINATE.getCode());
        printMessage(message);
    }

    public void printGameEnd() {
        printMessage(GAME_END_MESSAGE);
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

    private void printBallAndStrike(boolean isStrike, boolean isBall, int numberOfStrike, int numberOfBall) {
        if (isStrike && isBall) {
            message = String.format(RESULT_BALL_AND_STRIKE_MESSAGE, numberOfBall, numberOfStrike);
            printMessage(message);
        }
    }

    private void printNothing(boolean isStrike, boolean isBall) {
        if (!isStrike && !isBall) {
            printMessage(RESULT_NOTHING_MESSAGE);
        }
    }

    private void printMessage(String message) {
        System.out.println(message);
    }
}
