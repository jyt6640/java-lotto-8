package lotto.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import lotto.dto.LottoPurchaseResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class LottoPurchaseServiceTest {
    private LottoPurchaseService machine;

    @BeforeEach
    public void setUp() {
        machine = new LottoPurchaseService();
    }

    @DisplayName("구입 금액에 따라 로또를 발행")
    @CsvSource({
            "1000, 1",
            "2000, 2",
            "4000, 4",
            "8000, 8",
            "16000, 16"
    })
    @ParameterizedTest
    void 구입_금액에_따라_로또를_발행(int inputAmount, int expectedCount) {
        //given&when
        LottoPurchaseResult result = machine.purchase(inputAmount);

        //then
        assertThat(result.getLottos().getLottos().size()).isEqualTo(expectedCount);
    }
}
