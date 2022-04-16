package baseball.constants.baseball;

public final class BaseballOption {
    public static final int DIGIT_NUMBER_LENGTH = 3;
    public static final String DIGIT_NUMBER_PATTERN = String.format("[1-9]{%d}", DIGIT_NUMBER_LENGTH);

    private BaseballOption() {
    }
}
