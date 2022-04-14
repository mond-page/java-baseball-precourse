package baseball.model.gamemachine.constants;

public enum GameState {
    RESTART(1),
    TERMINATE(2);

    private int code;

    GameState(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
