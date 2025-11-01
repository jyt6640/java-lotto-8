package lotto.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class BonusNumberTest {
    @DisplayName("BonusNumber 정상 생성")
    @Test
    void BonusNumber_정상_생성() {
        //given
        int input = 42;

        //when&then
        assertThatCode(() -> new BonusNumber(input))
                .doesNotThrowAnyException();
    }

    @DisplayName("보너스 넘버가 1에서 45 범위를 넘어갈 시 예외 발생")
    @ValueSource(ints = {0, 56, -1, -52})
    @ParameterizedTest
    void 보너스_넘버가_1에서_45_범위를_넘어갈_시_예외_발생 (int input) {
        //when&then
        assertThatThrownBy(() -> new BonusNumber(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 1~45 범위 값만 허용됩니다.");
    }
}
