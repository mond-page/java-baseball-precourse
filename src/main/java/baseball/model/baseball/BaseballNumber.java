package baseball.model.baseball;

import baseball.constants.baseball.BaseballMessage;
import baseball.constants.baseball.BaseballOption;
import camp.nextstep.edu.missionutils.Randoms;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class BaseballNumber {
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 9;
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
            number = Randoms.pickNumberInRange(MIN_NUMBER, MAX_NUMBER);
        } while (isValidateNumber(number));

        return number;
    }

    public void validate(String inputNumber) {
        checkFormatNumber(inputNumber);
        checkDuplicateNumber(inputNumber);
    }

    private void checkDuplicateNumber(String number) {
        Set<Character> numberSet = new HashSet<>();
        for (char digitNumber : number.toCharArray()) {
            numberSet.add(digitNumber);
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

    public String getNumber() {
        StringBuilder builder = new StringBuilder();
        numbers.forEach(builder::append);
        return builder.toString();
    }

    private boolean isValidateNumber(int number) {
        return isDuplicateNumber(number) || isOutOfBoundNumber(number);
    }

    private boolean isDuplicateNumber(int number) {
        return numbers.contains(number);
    }

    private boolean isOutOfBoundNumber(int number) {
        return number > MAX_NUMBER || number < MIN_NUMBER;
    }
}
