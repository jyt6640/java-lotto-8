package lotto.domain;

import static lotto.domain.exception.ErrorMessage.DUPLICATE_BONUS_NUMBER;
import static lotto.domain.exception.ErrorMessage.INVALID_NUMBER_RANGE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class BonusNumberTest {
    private Lotto defaultWinningNumbers;

    @BeforeEach
    void setUp() {
        defaultWinningNumbers = new Lotto(List.of(1,2,3,4,5,6));
    }

    @DisplayName("BonusNumber 정상 생성")
    @Test
    void BonusNumber_정상_생성() {
        //given
        int input = 42;

        //when&then
        assertThatCode(() -> new BonusNumber(input, defaultWinningNumbers))
                .doesNotThrowAnyException();
    }

    @DisplayName("보너스 넘버가 1에서 45 범위를 넘어갈 시 예외 발생")
    @ValueSource(ints = {0, 56, -1, -52})
    @ParameterizedTest
    void 보너스_넘버가_1에서_45_범위를_넘어갈_시_예외_발생 (int input) {
        //when&then
        assertThatThrownBy(() -> new BonusNumber(input, defaultWinningNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_NUMBER_RANGE.toString());
    }

    @DisplayName("당첨 번호와 보너스 번호가 겹치면 예외 발생")
    @Test
    void 당첨_번호와_보너스_번호가_겹치면_예외_발생() {
        //when&then
        assertThatThrownBy(() -> new BonusNumber(6, defaultWinningNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(DUPLICATE_BONUS_NUMBER.toString());
    }
}
