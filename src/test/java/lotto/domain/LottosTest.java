package lotto.domain;

import static lotto.domain.exception.ErrorMessage.INVALID_LOTTOS_SIZE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class LottosTest {
    private Lotto defaultWinningNumber;

    @BeforeEach
    void setUp() {
        defaultWinningNumber = new Lotto(List.of(1, 2, 3, 4, 5, 6));
    }

    @DisplayName("여러 로또 생성")
    @Test
    void 여러_로또_생성() {
        //given
        List<Lotto> input = List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                new Lotto(List.of(1, 2, 3, 5, 6, 7)),
                new Lotto(List.of(15, 23, 25, 42, 37, 32)),
                new Lotto(List.of(6, 32, 12, 34, 28, 16))
        );

        //when&then
        assertThatCode(() -> new Lottos(input)).doesNotThrowAnyException();
    }

    @DisplayName("로또 개수 반환")
    @Test
    void 로또_개수_반환() {
        //given
        List<Lotto> input = List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                new Lotto(List.of(1, 2, 3, 5, 6, 7)),
                new Lotto(List.of(15, 23, 25, 42, 37, 32)),
                new Lotto(List.of(6, 32, 12, 34, 28, 16))
        );

        //when
        Lottos result = new Lottos(input);

        //then
        assertThat(result.getLottos().size()).isEqualTo(4);
    }

    @DisplayName("로또 개수가 1개 미만일 때 예외 발생")
    @Test
    void 로또_개수가_1개_미만일_때_예외_발생() {
        //given
        List<Lotto> input = List.of();

        //when&then
        assertThatThrownBy(() -> new Lottos(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_LOTTOS_SIZE.toString());
    }

    @DisplayName("여러 장의 로또 당첨 통계 계산")
    @MethodSource("provideLottosCase")
    @ParameterizedTest
    void 여러_장의_로또_당첨_통계_계산(
            List<List<Integer>> lottoNumbers,
            int bonusNumber,
            Map<Rank, Integer> expectedStatistics
    ) {
        // given
        List<Lotto> lottos = lottoNumbers.stream()
                .map(Lotto::new)
                .toList();
        Lottos input = new Lottos(lottos);
        WinningLotto winningLotto = new WinningLotto(defaultWinningNumber, new BonusNumber(bonusNumber, defaultWinningNumber));

        // when
        Map<Rank, Integer> result = input.calculateStatistics(winningLotto);

        // then
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
                        7,
                        Map.of(
                                Rank.FIRST, 1,
                                Rank.SECOND, 1,
                                Rank.THIRD, 1,
                                Rank.FOURTH, 1,
                                Rank.FIFTH, 1
                        )
                ),
                Arguments.of(
                        List.of(
                                List.of(1, 2, 3, 4, 5, 6),
                                List.of(1, 2, 3, 4, 5, 6)
                        ),
                        7,
                        Map.of(
                                Rank.FIRST, 2,
                                Rank.SECOND, 0,
                                Rank.THIRD, 0,
                                Rank.FOURTH, 0,
                                Rank.FIFTH, 0
                        )
                ),
                Arguments.of(
                        List.of(
                                List.of(1, 2, 3, 4, 5, 7),
                                List.of(1, 2, 3, 4, 5, 10)
                        ),
                        7,
                        Map.of(
                                Rank.FIRST, 0,
                                Rank.SECOND, 1,
                                Rank.THIRD, 1,
                                Rank.FOURTH, 0,
                                Rank.FIFTH, 0
                        )
                ),
                Arguments.of(
                        List.of(
                                List.of(1, 2, 3, 4, 7, 10),
                                List.of(1, 2, 3, 4, 10, 11),
                                List.of(1, 2, 3, 7, 10, 11),
                                List.of(1, 2, 3, 10, 11, 12)
                        ),
                        7,
                        Map.of(
                                Rank.FIRST, 0,
                                Rank.SECOND, 0,
                                Rank.THIRD, 1,
                                Rank.FOURTH, 2,
                                Rank.FIFTH, 1
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
                        7,
                        Map.of(
                                Rank.FIRST, 0,
                                Rank.SECOND, 0,
                                Rank.THIRD, 0,
                                Rank.FOURTH, 0,
                                Rank.FIFTH, 1
                        )
                ),
                Arguments.of(
                        List.of(
                                List.of(10, 11, 12, 13, 14, 15),
                                List.of(20, 21, 22, 23, 24, 25),
                                List.of(30, 31, 32, 33, 34, 35)
                        ),
                        7,
                        Map.of(
                                Rank.FIRST, 0,
                                Rank.SECOND, 0,
                                Rank.THIRD, 0,
                                Rank.FOURTH, 0,
                                Rank.FIFTH, 0
                        )
                )
        );
    }
}
