package baseball.controller;

import baseball.constants.gamemachine.GameMachineMessage;
import baseball.constants.gamemachine.GameMachineState;
import baseball.model.baseball.Baseball;
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
        monitor.printVictory();
        continueGame();
    }

    private void continueGame() {
        monitor.printGameChoice();
        String gameStateNumber = Console.readLine();
        validateGameStateNumber(gameStateNumber);
        if (Integer.parseInt(gameStateNumber) == GameMachineState.RESTART.getCode()) {
            start();
        }
    }

    private void countInputNumber(String inputNumber) {
        baseball.count(inputNumber);
        monitor.printCountResult(baseball.getResult());
    }

    private void validateInputNumber(String inputNumber) {
        baseball.validate(inputNumber);
    }

    private void validateGameStateNumber(String gameStateNumber) {
        String regex = String.format("(%d|%d)", GameMachineState.RESTART.getCode(), GameMachineState.TERMINATE.getCode());

        if (!gameStateNumber.matches(regex)) {
            throw new IllegalArgumentException(GameMachineMessage.INVALID_NUMBER_FORMAT);
        }
    }

    private boolean isRunning() {
        return !baseball.isAnswer();
    }
}
