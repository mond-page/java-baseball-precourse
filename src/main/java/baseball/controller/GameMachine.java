package baseball.controller;

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
    }

    private boolean isRunning() {
        return !baseball.isAnswer();
    }

    private void countInputNumber(String inputNumber) {
        baseball.count(inputNumber);
        monitor.printCountResult(baseball.getResult());
    }

    private void validateInputNumber(String inputNumber) {
        baseball.validate(inputNumber);
    }
}
