package baseball.constants.gamemachine;

import java.util.HashMap;
import java.util.Map;

public enum GameMachineState {
    RESTART("1"),
    TERMINATE("2"),
    UNKNOWN("UNKNOWN");

    private final String code;
    private static final Map<String, GameMachineState> descriptions = getDescriptions();

    GameMachineState(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static GameMachineState findByCode(String code) {
        if (descriptions.containsKey(code)) {
            return descriptions.get(code);
        }

        return UNKNOWN;
    }

    private static Map<String, GameMachineState> getDescriptions() {
        Map<String, GameMachineState> descriptions = new HashMap<>();
        for (GameMachineState gameMachineState : values()) {
            descriptions.put(gameMachineState.code, gameMachineState);
        }

        return descriptions;
    }
}
