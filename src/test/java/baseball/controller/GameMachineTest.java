package baseball.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class GameMachineTest {

    @Test
    void 게임머신_생성() {
        GameMachine gameMachine = new GameMachine();

        assertThat(gameMachine).isNotNull();
    }
}
