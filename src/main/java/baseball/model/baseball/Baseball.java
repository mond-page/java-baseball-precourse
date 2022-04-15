package baseball.model.baseball;

import baseball.constants.baseball.BaseballMessage;
import baseball.constants.baseball.BaseballOption;
import java.util.HashSet;
import java.util.Set;

public class Baseball {
    private final BaseballNumber baseballNumber;
    private BaseballCounter baseballCounter;

    private Baseball() {
        validateDigitNumberLength();
        baseballNumber = BaseballNumber.create();
    }

    public static Baseball start() {
        return new Baseball();
    }

    public void count(String inputNumber) {
        baseballCounter = BaseballCounter.create(baseballNumber.getNumber());
        baseballCounter.count(inputNumber);
    }

    public BaseballCounter getResult() {
        return baseballCounter;
    }

    public boolean isAnswer() {
        return baseballCounter != null && baseballCounter.isAllStrike();
    }

    public void validate(String inputNumber) {
        checkFormatNumber(inputNumber);
        checkDuplicateNumber(inputNumber);
    }

    private void checkDuplicateNumber(String number) {
        Set<Integer> numberSet = new HashSet<>();
        for (char digitNumber : number.toCharArray()) {
            numberSet.add((int) digitNumber);
        }

        if (number.length() != numberSet.size()) {
            throw new IllegalArgumentException(BaseballMessage.DUPLICATE_DIGIT_NUMBER);
        }
    }

    private void checkFormatNumber(String number) {
        String regex = String.format("[1-9]{%d}", BaseballOption.DIGIT_NUMBER_LENGTH);
        if (!number.matches(regex)) {
            throw new IllegalArgumentException(BaseballMessage.INVALID_NUMBER_FORMAT);
        }
    }

    private void validateDigitNumberLength() {
        if (BaseballOption.DIGIT_NUMBER_LENGTH > 9 || BaseballOption.DIGIT_NUMBER_LENGTH < 0) {
            throw new IllegalArgumentException(BaseballMessage.INVALID_DIGIT_NUMBER_LENGTH);
        }
    }
}
