package baseball.controller;

import baseball.model.baseball.BaseballNumber;

public class GameMachine {

    public void start() {
        BaseballNumber number = BaseballNumber.create();
        System.out.println(number);
    }
}
