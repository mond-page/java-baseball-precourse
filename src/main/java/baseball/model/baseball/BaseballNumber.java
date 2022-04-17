package baseball.model.baseball;

import baseball.constants.baseball.BaseballMessage;
import baseball.constants.baseball.BaseballOption;
import camp.nextstep.edu.missionutils.Randoms;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class BaseballNumber {
    private List<Integer> numbers;

    private BaseballNumber() {
        generateNumber();
    }

    public static BaseballNumber create() {
        return new BaseballNumber();
    }

    private void generateNumber() {
        numbers = new LinkedList<>();
        while (numbers.size() < BaseballOption.DIGIT_NUMBER_LENGTH) {
            int number = pickNumber();
            numbers.add(number);
        }
    }

    private int pickNumber() {
        int number;
        do {
            number = Randoms.pickNumberInRange(BaseballOption.MIN_NUMBER, BaseballOption.MAX_NUMBER);
        } while (isValidateNumber(number));

        return number;
    }

    public void validate(String inputNumber) {
        checkFormatNumber(inputNumber);
        checkDuplicateNumber(inputNumber);
    }

    private void checkDuplicateNumber(String number) {
        Set<Integer> numberSet = new HashSet<>();
        for (char digitNumber : number.toCharArray()) {
            numberSet.add(Character.getNumericValue(digitNumber));
        }

        if (number.length() != numberSet.size()) {
            throw new IllegalArgumentException(BaseballMessage.DUPLICATE_DIGIT_NUMBER);
        }
    }

    private void checkFormatNumber(String number) {
        if (!number.matches(BaseballOption.DIGIT_NUMBER_PATTERN)) {
            throw new IllegalArgumentException(BaseballMessage.INVALID_NUMBER_FORMAT);
        }
    }

    public List<Integer> getNumber() {
        return numbers;
    }

    private boolean isValidateNumber(int number) {
        return isDuplicateNumber(number) || isOutOfBoundNumber(number);
    }

    private boolean isDuplicateNumber(int number) {
        return numbers.contains(number);
    }

    private boolean isOutOfBoundNumber(int number) {
        return number > BaseballOption.MAX_NUMBER || number < BaseballOption.MIN_NUMBER;
    }
}
