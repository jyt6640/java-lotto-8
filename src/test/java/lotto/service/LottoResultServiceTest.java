package lotto.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import java.util.stream.Stream;
import lotto.domain.Lotto;
import lotto.domain.Rank;
import lotto.domain.WinningLotto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
}
