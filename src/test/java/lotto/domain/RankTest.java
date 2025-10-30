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

    @DisplayName("다섯개 일치하고 보너스 번호가 있으면 2등")
    @Test
    void 다섯개_일치하고_보너스_번호가_포함되어_있으면_2등() {
        //given&when
        Rank input = Rank.of(5, true);

        //then
        assertThat(input).isEqualTo(Rank.SECOND);
        assertThat(input.getPrize()).isEqualTo(30_000_000L);
    }
}
