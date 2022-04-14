package baseball.controller;

import baseball.model.baseball.Baseball;
import baseball.model.gamemachine.constants.GameMessage;
import baseball.model.gamemachine.constants.GameState;
import baseball.view.Monitor;
import camp.nextstep.edu.missionutils.Console;

public class GameMachine {
    private final Monitor monitor;
    private Baseball baseball;

    public GameMachine() {
        this.monitor = new Monitor();
    }

    public void start() {
        baseball = Baseball.start();
        inputNumber();
    }

    private void inputNumber() {
        while (isRunning()) {
            monitor.printInputNumber();
            String inputNumber = Console.readLine();
            validateInputNumber(inputNumber);
            countInputNumber(inputNumber);
        }
        continueGame();
    }

    private void continueGame() {
        monitor.printGameChoice();
        String gameStateNumber = Console.readLine();
        validateGameStateNumber(gameStateNumber);
        if (Integer.parseInt(gameStateNumber) == GameState.TERMINATE.getCode()) {
            monitor.printGameEnd();
            return;
        }
        start();
    }

    private void countInputNumber(String inputNumber) {
        baseball.count(inputNumber);
        monitor.printCountResult(baseball.getResult());
    }

    private void validateInputNumber(String inputNumber) {
        baseball.validate(inputNumber);
    }

    private void validateGameStateNumber(String gameStateNumber) {
        String regex = String.format("(%d|%d)", GameState.RESTART.getCode(), GameState.TERMINATE.getCode());

        if (!gameStateNumber.matches(regex)) {
            throw new IllegalArgumentException(GameMessage.INVALID_NUMBER_FORMAT);
        }
    }

    private boolean isRunning() {
        return !baseball.isAnswer();
    }
}
