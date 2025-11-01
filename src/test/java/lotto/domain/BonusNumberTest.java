package lotto.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
}
