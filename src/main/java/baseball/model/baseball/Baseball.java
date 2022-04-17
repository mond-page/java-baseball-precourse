package baseball.model.baseball;

import baseball.constants.baseball.BaseballMessage;
import baseball.constants.baseball.BaseballOption;
import java.util.ArrayList;
import java.util.List;

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

    public void validateNumber(String inputNumber) {
        baseballNumber.validate(inputNumber);
    }

    public void count(String inputNumber) {
        baseballCounter = BaseballCounter.create(baseballNumber.getNumber());
        baseballCounter.count(convertStringNumberToList(inputNumber));
    }

    public BaseballCounter getResult() {
        return baseballCounter;
    }

    public boolean isAnswer() {
        return baseballCounter != null && baseballCounter.isAllStrike();
    }

    private void validateDigitNumberLength() {
        if (BaseballOption.DIGIT_NUMBER_LENGTH > 9 || BaseballOption.DIGIT_NUMBER_LENGTH < 0) {
            throw new IllegalArgumentException(BaseballMessage.INVALID_DIGIT_NUMBER_LENGTH);
        }
    }

    private List<Integer> convertStringNumberToList(String stringNumber) {
        List<Integer> number = new ArrayList<>();
        for (char digitNumber : stringNumber.toCharArray()) {
            number.add(Character.getNumericValue(digitNumber));
        }

        return number;
    }
}
