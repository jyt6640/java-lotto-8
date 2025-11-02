package lotto.util;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class InputParserTest {
    @DisplayName("보너스 번호 1개 분리")
    @Test
    void 보너스_번호_1개_분리() {
        //given
        List<Integer> input = List.of(1, 2, 3, 4, 5, 6, 7);

        //when
        int bonusNumber = InputParser.extractBonusNumber(input);

        //then
        assertThat(bonusNumber).isEqualTo(input.getLast());
    }

    @DisplayName("당첨 번호 6개 분리")
    @Test
    void 당첨_번호_6개_분리() {
        //given
        List<Integer> input = List.of(1, 2, 3, 4, 5, 6, 7);

        //when
        List<Integer> winningNumbers = InputParser.extractWinningNumbers(input);

        //then
        assertThat(winningNumbers.size()).isEqualTo(6);
        assertThat(winningNumbers).isEqualTo(input.subList(0,6));
    }

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
}
