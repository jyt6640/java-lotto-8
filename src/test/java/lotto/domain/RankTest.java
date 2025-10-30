package lotto.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RankTest {
    @DisplayName("여섯개 일치 시 1등")
    @Test
    void 여섯개_일치_시_1등() {
        //given&when
        Rank input = Rank.of(6, false);

        //then
        assertThat(input).isEqualTo(Rank.FIRST);
        assertThat(input.getPrize()).isEqualTo(2_000_000_000L);
    }
}
