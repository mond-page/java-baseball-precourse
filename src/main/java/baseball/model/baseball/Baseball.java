package baseball.model.baseball;

import baseball.model.baseball.constants.BaseballMessage;
import baseball.model.baseball.constants.BaseballOption;
import java.util.HashSet;
import java.util.Set;

public class Baseball {
    private BaseballNumber baseballNumber;

    private Baseball() {
        baseballNumber = BaseballNumber.create();
    }

    public static Baseball start() {
        return new Baseball();
    }

    public void validate(String number) {
        checkFormatNumber(number);
        checkDuplicateNumber(number);
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
