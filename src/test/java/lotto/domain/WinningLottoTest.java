package lotto.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class WinningLottoTest {
    private Lotto defaultWinningNumber;

    @BeforeEach
    void setUp() {
        defaultWinningNumber = new Lotto(List.of(1, 2, 3, 4, 5, 6));
    }

    @DisplayName("당첨 번호와 일치하는 개수 반환")
    @MethodSource("provideMatchCases")
    @ParameterizedTest
    void 당첨_번호와_일치하는_개수_반환(
            Lotto myNumbers,
            List<Integer> winningNumbers,
            int bonusNumber,
            int expectedCount
    ) {
        //given
        WinningLotto input = new WinningLotto(new Lotto(winningNumbers), new BonusNumber(bonusNumber, new Lotto(winningNumbers)));

        //when
        int result = input.countMatches(myNumbers);

        //then
        assertThat(result).isEqualTo(expectedCount);
    }

    private static Stream<Arguments> provideMatchCases() {
        return Stream.of(
                Arguments.of(
                        new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                        List.of(1, 2, 3, 4, 5, 6),
                        30,
                        6
                ),
                Arguments.of(
                        new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                        List.of(1, 2, 3, 4, 5, 7),
                        30,
                        5
                ),
                Arguments.of(
                        new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                        List.of(1, 2, 3, 4, 8, 7),
                        30,
                        4
                ),
                Arguments.of(
                        new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                        List.of(1, 2, 3, 9, 8, 7),
                        30,
                        3
                ),
                Arguments.of(
                        new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                        List.of(1, 2, 10, 9, 8, 7),
                        30,
                        2
                ),
                Arguments.of(
                        new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                        List.of(1, 11, 10, 9, 8, 7),
                        30,
                        1
                ),
                Arguments.of(
                        new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                        List.of(12, 11, 10, 9, 8, 7),
                        30,
                        0
                ),
                Arguments.of(
                        new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                        List.of(6, 5, 4, 3, 2, 1),
                        30,
                        6
                )
        );
    }

    @DisplayName("보너스 번호가 있는지 확인")
    @Test
    void 보너스_번호가_있는지_확인() {
        //given
        WinningLotto winningLotto = new WinningLotto(defaultWinningNumber, new BonusNumber(7, defaultWinningNumber));
        Lotto myLotto = new Lotto(List.of(1, 2, 3, 4, 5, 7));

        //when
        boolean result = winningLotto.hasBonusNumber(myLotto);

        assertThat(result).isTrue();
    }

    @DisplayName("하나의 로또에 대해서 로또 번호와 당첨 번호를 비교하여 순위 반환")
    @MethodSource("provideLottos")
    @ParameterizedTest
    void 로또_번호와_당첨_번호를_비교하여_순위_반환(
            List<Integer> myNumber,
            int bonusNumber,
            Rank expectedRank
    ) {
        //given
        Lotto myLotto = new Lotto(myNumber);
        WinningLotto winningLotto = new WinningLotto(defaultWinningNumber, new BonusNumber(bonusNumber, defaultWinningNumber));

        //when
        Rank result = winningLotto.determineRank(myLotto);

        //then
        assertThat(result).isEqualTo(expectedRank);
    }

    private static Stream<Arguments> provideLottos() {
        return Stream.of(
                Arguments.of(
                        List.of(1, 2, 3, 4, 5, 6),
                        7,
                        Rank.FIRST
                ),
                Arguments.of(
                        List.of(1, 2, 3, 4, 5, 7),
                        7,
                        Rank.SECOND
                ),
                Arguments.of(
                        List.of(1, 2, 3, 4, 5, 10),
                        7,
                        Rank.THIRD
                ),
                Arguments.of(
                        List.of(1, 2, 3, 4, 10, 11),
                        7,
                        Rank.FOURTH
                ),
                Arguments.of(
                        List.of(1, 2, 3, 10, 11, 12),
                        7,
                        Rank.FIFTH
                ),
                Arguments.of(
                        List.of(1, 2, 7, 10, 11, 12),
                        7,
                        Rank.FIFTH
                ),
                Arguments.of(
                        List.of(1, 2, 10, 11, 12, 13),
                        7,
                        Rank.NONE
                ),
                Arguments.of(
                        List.of(10, 11, 12, 13, 14, 15),
                        7,
                        Rank.NONE
                )
        );
    }
}
