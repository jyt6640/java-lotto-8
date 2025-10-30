package lotto.domain;

import java.util.stream.Stream;
import lotto.Lotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class LottoTest {
    @Test
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("당첨 번호와 일치하는 개수 반환")
    @MethodSource("provideMatchCases")
    @ParameterizedTest
    void 당첨_번호와_일치하는_개수_반환(
            List<Integer> myNumbers,
            List<Integer> winningNumber,
            int expectedCount
    ) {
        //given
        Lotto input = new Lotto(myNumbers);

        //when
        int result = input.countMatches(winningNumber);

        //then
        assertThat(result).isEqualTo(expectedCount);
    }

    private static Stream<Arguments> provideMatchCases() {
        return Stream.of(
                Arguments.of(
                        List.of(1, 2, 3, 4, 5, 6),
                        List.of(1, 2, 3, 4, 5, 6),
                        6
                ),
                Arguments.of(
                        List.of(1, 2, 3, 4, 5, 6),
                        List.of(1, 2, 3, 4, 5, 7),
                        5
                ),
                Arguments.of(
                        List.of(1, 2, 3, 4, 5, 6),
                        List.of(1, 2, 3, 4, 8, 7),
                        4
                ),
                Arguments.of(
                        List.of(1, 2, 3, 4, 5, 6),
                        List.of(1, 2, 3, 9, 8, 7),
                        3
                ),
                Arguments.of(
                        List.of(1, 2, 3, 4, 5, 6),
                        List.of(1, 2, 10, 9, 8, 7),
                        2
                ),
                Arguments.of(
                        List.of(1, 2, 3, 4, 5, 6),
                        List.of(1, 11, 10, 9, 8, 7),
                        1
                ),
                Arguments.of(
                        List.of(1, 2, 3, 4, 5, 6),
                        List.of(12, 11, 10, 9, 8, 7),
                        0
                ),
                Arguments.of(
                        List.of(1, 2, 3, 4, 5, 6),
                        List.of(6, 5, 4, 3, 2, 1),
                        6
                )
        );
    }
}
