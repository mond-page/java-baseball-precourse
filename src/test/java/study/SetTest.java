package study;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class SetTest {
    private Set<Integer> numbers;

    @BeforeEach
    void 초기_데이터_구성() {
        numbers = new HashSet<>();
        numbers.add(1);
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
    }

    @Test
    void 사이즈_확인() {
        int size = numbers.size();

        assertThat(size).isEqualTo(3);
    }

    @ParameterizedTest(name = "제공하는 값({0})이 포함 여부")
    @ValueSource(ints = {1, 2, 3})
    void 제공하는_값이_모두_성공하는_경우(int number) {
        boolean result = numbers.contains(number);

        assertTrue(result);
    }

    @ParameterizedTest(name = "제공하는 값({0})이 예상된 결과 {1} 포함 여부")
    @CsvSource(value = {"1:true", "2:true", "3:true", "4:false", "5:false"}, delimiter = ':')
    void 제공하는_값이_성공하거나_실패하는_경우(int number, boolean expectedResult) {
        boolean result = numbers.contains(number);

        assertEquals(result, expectedResult);
    }
}
