package baseball.model.baseball;

import baseball.constants.baseball.BaseballMessage;
import baseball.constants.baseball.BaseballOption;
import java.util.HashSet;
import java.util.Set;

public class Baseball {
    private BaseballNumber baseballNumber;
    private BaseballCounter baseballCounter;

    private Baseball() {
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
        return baseballCounter != null && baseballCounter.getStrike() == BaseballOption.DIGIT_NUMBER;
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
        if (!number.matches("[1-9]{" + BaseballOption.DIGIT_NUMBER + "}")) {
            throw new IllegalArgumentException(BaseballMessage.INVALID_NUMBER_FORMAT);
        }
    }
}
