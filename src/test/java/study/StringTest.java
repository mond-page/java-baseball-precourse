package study;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StringTest {

    @Test
    void 숫자열거형_문자열을_구분자로_나누는_경우() {
        String str = "1,2";
        String[] numbers = str.split(",");

        assertThat(numbers).containsExactly("1", "2");
    }

    @Test
    void 단일숫자_문자열을_구분자로_나누는_경우() {
        String str = "1";
        String[] number = str.split(",");

        assertThat(number).contains("1");
    }

    @Test
    void 숫자열거형_문자열의_괄호를_제거하는_경우() {
        String str = "(1,2)";
        String trimBracket = str.substring(1, str.length() - 1);

        assertThat(trimBracket).isEqualTo("1,2");
    }

    @Test
    @DisplayName("charAt 메소드를 사용하여 문자열을 꺼내온다.")
    void 문자열에서_문자를_가져오는_경우() {
        String str = "abc";

        assertAll(str,
                () -> assertThat(str.charAt(0)).isEqualTo('a'),
                () -> assertThat(str.charAt(1)).isEqualTo('b'),
                () -> assertThat(str.charAt(2)).isEqualTo('c')
        );
    }

    @Test
    @DisplayName("charAt 메소드는 인덱스를 벗어나면 StringIndexOutOfBoundsException 예외가 발생한다.")
    void 문자열에서_문자를_가져오다_에러나는_경우() {
        String str = "abc";
        int index = 5;

        assertThatThrownBy(() -> str.charAt(index))
                .isInstanceOf(StringIndexOutOfBoundsException.class)
                .hasMessageContaining("String index out of range: %d", index);

        assertThatExceptionOfType(StringIndexOutOfBoundsException.class)
                .isThrownBy(() -> str.charAt(index))
                .withMessageContaining("String index out of range: %d", index);
    }
}
