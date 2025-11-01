package lotto.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.domain.Rank;
import lotto.domain.WinningLotto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class LottoResultServiceTest {
    private LottoResultService lottoResultService;

    @BeforeEach
    public void setUp(){
        lottoResultService = new LottoResultService();
    }

    @DisplayName("하나의 로또에 대해서 로또 번호와 당첨 번호를 비교하여 순위 반환")
    @MethodSource("provideLottos")
    @ParameterizedTest
    void 로또_번호와_당첨_번호를_비교하여_순위_반환(
        List<Integer> myNumber,
        List<Integer> winningNumber,
        int bonusNumber,
        Rank expectedRank
    ) {
        //given
        Lotto myLotto = new Lotto(myNumber);
        WinningLotto winningLotto = new WinningLotto(winningNumber, bonusNumber);

        //when
        Rank result = lottoResultService.getRank(winningLotto, myLotto);

        //then
        assertThat(result).isEqualTo(expectedRank);
    }

    private static Stream<Arguments> provideLottos() {
        return Stream.of(
                Arguments.of(
                        List.of(1, 2, 3, 4, 5, 6),
                        List.of(1, 2, 3, 4, 5, 6),
                        7,
                        Rank.FIRST
                ),
                Arguments.of(
                        List.of(1, 2, 3, 4, 5, 7),
                        List.of(1, 2, 3, 4, 5, 6),
                        7,
                        Rank.SECOND
                ),
                Arguments.of(
                        List.of(1, 2, 3, 4, 5, 10),
                        List.of(1, 2, 3, 4, 5, 6),
                        7,
                        Rank.THIRD
                ),
                Arguments.of(
                        List.of(1, 2, 3, 4, 10, 11),
                        List.of(1, 2, 3, 4, 5, 6),
                        7,
                        Rank.FOURTH
                ),
                Arguments.of(
                        List.of(1, 2, 3, 10, 11, 12),
                        List.of(1, 2, 3, 4, 5, 6),
                        7,
                        Rank.FIFTH
                ),
                Arguments.of(
                        List.of(1, 2, 7, 10, 11, 12),
                        List.of(1, 2, 3, 4, 5, 6),
                        7,
                        Rank.FIFTH
                ),
                Arguments.of(
                        List.of(1, 2, 10, 11, 12, 13),
                        List.of(1, 2, 3, 4, 5, 6),
                        7,
                        Rank.NONE
                ),
                Arguments.of(
                        List.of(10, 11, 12, 13, 14, 15),
                        List.of(1, 2, 3, 4, 5, 6),
                        7,
                        Rank.NONE
                )
        );
    }

    @DisplayName("여러 장의 로또 당첨 통계 계산")
    @MethodSource("provideLottosCase")
    @ParameterizedTest
    void 여러_장의_로또_당첨_통계_계산(
            List<List<Integer>> lottoNumbers,
            List<Integer> winningNumber,
            int bonusNumber,
            Map<Rank, Integer> expectedStatistics
    ) {
        //given
        List<Lotto> lottos = lottoNumbers.stream()
                .map(Lotto::new)
                .toList();
        Lottos input = new Lottos(lottos);
        WinningLotto winningLotto = new WinningLotto(winningNumber, bonusNumber);

        //when
        Map<Rank, Integer> result = lottoResultService.getStatistics(winningLotto, input);

        //when
        assertThat(result).isEqualTo(expectedStatistics);
    }
    private static Stream<Arguments> provideLottosCase() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                List.of(1, 2, 3, 4, 5, 6),
                                List.of(1, 2, 3, 4, 5, 7),
                                List.of(1, 2, 3, 4, 5, 10),
                                List.of(1, 2, 3, 4, 10, 11),
                                List.of(1, 2, 3, 10, 11, 12),
                                List.of(10, 11, 12, 13, 14, 15)
                        ),
                        List.of(1, 2, 3, 4, 5, 6),
                        7,
                        Map.of(
                                Rank.FIRST, 1,
                                Rank.SECOND, 1,
                                Rank.THIRD, 1,
                                Rank.FOURTH, 1,
                                Rank.FIFTH, 1,
                                Rank.NONE, 1
                        )
                ),
                Arguments.of(
                        List.of(
                                List.of(1, 2, 3, 4, 5, 6),
                                List.of(1, 2, 3, 4, 5, 6)
                        ),
                        List.of(1, 2, 3, 4, 5, 6),
                        7,
                        Map.of(
                                Rank.FIRST, 2,
                                Rank.SECOND, 0,
                                Rank.THIRD, 0,
                                Rank.FOURTH, 0,
                                Rank.FIFTH, 0,
                                Rank.NONE, 0
                        )
                ),
                Arguments.of(
                        List.of(
                                List.of(1, 2, 3, 4, 5, 7),
                                List.of(1, 2, 3, 4, 5, 10)
                        ),
                        List.of(1, 2, 3, 4, 5, 6),
                        7,
                        Map.of(
                                Rank.FIRST, 0,
                                Rank.SECOND, 1,
                                Rank.THIRD, 1,
                                Rank.FOURTH, 0,
                                Rank.FIFTH, 0,
                                Rank.NONE, 0
                        )
                ),
                Arguments.of(
                        List.of(
                                List.of(1, 2, 3, 4, 7, 10),
                                List.of(1, 2, 3, 4, 10, 11),
                                List.of(1, 2, 3, 7, 10, 11),
                                List.of(1, 2, 3, 10, 11, 12)
                        ),
                        List.of(1, 2, 3, 4, 5, 6),
                        7,
                        Map.of(
                                Rank.FIRST, 0,
                                Rank.SECOND, 0,
                                Rank.THIRD, 1,
                                Rank.FOURTH, 2,
                                Rank.FIFTH, 1,
                                Rank.NONE, 0
                        )
                ),
                Arguments.of(
                        List.of(
                                List.of(8, 21, 23, 41, 42, 43),
                                List.of(3, 5, 11, 16, 32, 38),
                                List.of(7, 11, 16, 35, 36, 44),
                                List.of(1, 8, 11, 31, 41, 42),
                                List.of(13, 14, 16, 38, 42, 45),
                                List.of(7, 11, 30, 40, 42, 43),
                                List.of(2, 13, 22, 32, 38, 45),
                                List.of(1, 3, 5, 14, 22, 45)
                        ),
                        List.of(1, 2, 3, 4, 5, 6),
                        7,
                        Map.of(
                                Rank.FIRST, 0,
                                Rank.SECOND, 0,
                                Rank.THIRD, 0,
                                Rank.FOURTH, 0,
                                Rank.FIFTH, 1,
                                Rank.NONE, 7
                        )
                ),
                Arguments.of(
                        List.of(
                                List.of(10, 11, 12, 13, 14, 15),
                                List.of(20, 21, 22, 23, 24, 25),
                                List.of(30, 31, 32, 33, 34, 35)
                        ),
                        List.of(1, 2, 3, 4, 5, 6),
                        7,
                        Map.of(
                                Rank.FIRST, 0,
                                Rank.SECOND, 0,
                                Rank.THIRD, 0,
                                Rank.FOURTH, 0,
                                Rank.FIFTH, 0,
                                Rank.NONE, 3
                        )
                )
        );
    }

    @DisplayName("당첨 통계 기반으로 수익률 계산")
    @Test
    void 당첨_통계_기반으로_수익률_계산() {
        //given
        int totalPurchaseAmount = 6 * 1000;

        Map<Rank, Integer> statistics = new EnumMap<>(Rank.class);
        for (Rank rank : Rank.values()) {
            statistics.put(rank, 0);
        }

        statistics.put(Rank.FIRST, 1);
        statistics.put(Rank.SECOND, 1);
        statistics.put(Rank.THIRD, 1);
        statistics.put(Rank.FOURTH, 1);
        statistics.put(Rank.FIFTH, 1);
        statistics.put(Rank.NONE, 1);

        //when
        double result = lottoResultService.calculateProfitRate(statistics, totalPurchaseAmount);

        //then
        assertThat(result).isEqualTo(338592.5);
    }

}
