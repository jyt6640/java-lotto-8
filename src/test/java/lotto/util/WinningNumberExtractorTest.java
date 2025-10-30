package lotto.util;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinningNumberExtractorTest {
    @DisplayName("보너스 번호 1개 분리")
    @Test
    void 보너스_번호_1개_분리() {
        //given
        List<Integer> input = List.of(1, 2, 3, 4, 5, 6, 7);

        //when
        int bonusNumber = WinningNumberExtractor.extractBonusNumber(input);

        //then
        assertThat(bonusNumber).isEqualTo(input.getLast());
    }

    @DisplayName("당첨 번호 6개 분리")
    @Test
    void 당첨_번호_6개_분리() {
        //given
        List<Integer> input = List.of(1, 2, 3, 4, 5, 6, 7);

        //when
        List<Integer> winningNumbers = WinningNumberExtractor.extractWinningNumbers(input);

        //then
        assertThat(winningNumbers.size()).isEqualTo(6);
        assertThat(winningNumbers).isEqualTo(input.subList(0,6));
    }
}
