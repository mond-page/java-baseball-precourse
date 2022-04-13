package baseball;

import baseball.controller.GameMachine;

public class Application {
    public static void main(String[] args) {
        GameMachine machine = new GameMachine();
        machine.start();
    }
}
