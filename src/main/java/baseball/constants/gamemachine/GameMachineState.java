package baseball.constants.gamemachine;

public enum GameMachineState {
    RESTART(1),
    TERMINATE(2);

    private final int code;

    GameMachineState(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
