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
        monitor.printInputNumber();
        inputNumber();
    }

    private void inputNumber() {
        String inputNumber = Console.readLine();
        validateInputNumber(inputNumber);
        baseball.count(inputNumber);
        monitor.printCountResult(baseball.getResult());
    }

    private void validateInputNumber(String inputNumber) {
        baseball.validate(inputNumber);
    }
}
