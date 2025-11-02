package lotto.util;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class InputParserTest {
    @DisplayName("당첨 번호 입력 후 컬렉션으로 변환")
    @Test
    void 당첨_번호_입력_후_컬렉션으로_변환() {
        //given
        String input = "1,2,3,4,5,6";

        //when
        List<Integer> result = InputParser.parseWinningNumbers(input);

        //then
        assertThat(result).isEqualTo(List.of(1, 2, 3, 4, 5, 6));
    }

    @DisplayName("문자열 입력 후 int로 변환")
    @Test
    void 문자열_입력_후_int로_변환() {
        //given
        String input = "7";

        //when
        int result = InputParser.parseToInt(input);

        //then
        assertThat(result).isEqualTo(7);
    }

    @DisplayName("int로 변환 시 오버플로우 발생 시 예외 발생")
    @Test
    void int로_변환_시_오버플로우_발생_시_예외_발생() {
        //given
        String input = "99999999999999";

        //when&then
        assertThatThrownBy(() -> InputParser.parseToInt(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 입력값은 int 범위를 넘어갈 수 없습니다.");
    }
}
