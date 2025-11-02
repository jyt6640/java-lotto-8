package lotto.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import lotto.dto.LottoPurchaseResult;
import lotto.generator.FixedNumberGenerator;
import lotto.generator.NumberGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class LottoPurchaseServiceTest {
    private LottoPurchaseService machine;

    @BeforeEach
    public void setUp() {
        NumberGenerator numberGenerator = new FixedNumberGenerator(
                List.of(1, 2, 3, 4, 5, 6)
        );
        machine = new LottoPurchaseService(numberGenerator);
    }

    @DisplayName("구입 금액에 따라 로또를 발행")
    @ParameterizedTest
    @CsvSource({
            "1000, 1",
            "2000, 2",
            "4000, 4",
            "8000, 8",
            "16000, 16"
    })
    void 구입_금액에_따라_로또를_발행(int inputAmount, int expectedCount) {
        // when
        LottoPurchaseResult result = machine.purchase(inputAmount);

        // then
        assertThat(result.getLottos().getLottos()).hasSize(expectedCount);
        assertThat(result.getCount()).isEqualTo(expectedCount);
    }
}