package baseball.model.baseball;

import baseball.model.baseball.constants.BaseballMessage;
import baseball.model.baseball.constants.BaseballOption;
import camp.nextstep.edu.missionutils.Randoms;
import java.util.LinkedList;
import java.util.List;

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

    public String getNumber() {
        StringBuilder builder = new StringBuilder();
        numbers.forEach(builder::append);
        return builder.toString();
    }

    private void generateNumber() {
        numbers = new LinkedList<>();
        while (numbers.size() < BaseballOption.DIGIT_NUMBER) {
            int number = pickNumber();
            validate(number);
            numbers.add(number);
        }
    }

    private int pickNumber() {
        int number;
        do {
            number = Randoms.pickNumberInRange(MIN_NUMBER, MAX_NUMBER);
        } while (numbers.contains(number));

        return number;
    }

    public static void validate(int number) {
        checkOutOfBound(number);
    }

    private static void checkOutOfBound(int number) {
        if (number > MAX_NUMBER || number < MIN_NUMBER) {
            throw new IllegalArgumentException(BaseballMessage.OUT_OF_BOUND_NUMBER);
        }
    }
}
