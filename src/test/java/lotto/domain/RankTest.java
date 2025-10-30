package lotto.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

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

    @DisplayName("보너스 번호가 있을 시 등수 변화")
    @Test
    void 보너스_번호가_있을_시_등수_변화() {
        //given&when
        Rank input = Rank.of(2, true);

        //then
        assertThat(input).isEqualTo(Rank.FIFTH);
        assertThat(input.getPrize()).isEqualTo(5_000L);
    }


    @DisplayName("일치 개수에 따른 등수 반환")
    @MethodSource("provideRanks")
    @ParameterizedTest
    void 일치_개수에_따른_등수_반환(
            int matchCount,
            boolean hasBonusNumber,
            Rank expectedRank,
            long expectedPrize
    ) {
        //given&when
        Rank input = Rank.of(matchCount, hasBonusNumber);

        //then
        assertThat(input).isEqualTo(expectedRank);
        assertThat(input.getPrize()).isEqualTo(expectedPrize);
    }

    private static Stream<Arguments> provideRanks() {
        return Stream.of(
                Arguments.of(0, false, Rank.NONE, 0L),
                Arguments.of(1, false, Rank.NONE, 0L),
                Arguments.of(2, false, Rank.NONE, 0L),
                Arguments.of(3, false, Rank.FIFTH, 5_000L),
                Arguments.of(4, false, Rank.FOURTH, 50_000L),
                Arguments.of(5, false, Rank.THIRD, 1_500_000L)
        );
    }
}
