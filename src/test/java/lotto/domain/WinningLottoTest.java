package lotto.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class WinningLottoTest {
    @DisplayName("당첨 번호와 일치하는 개수 반환")
    @MethodSource("provideMatchCases")
    @ParameterizedTest
    void 당첨_번호와_일치하는_개수_반환(
            List<Integer> myNumbers,
            List<Integer> winningNumber,
            int bonusNumber,
            int expectedCount
    ) {
        //given
        WinningLotto input = new WinningLotto(winningNumber, bonusNumber);

        //when
        int result = input.countMatches(myNumbers);

        //then
        assertThat(result).isEqualTo(expectedCount);
    }

    private static Stream<Arguments> provideMatchCases() {
        return Stream.of(
                Arguments.of(
                        List.of(1, 2, 3, 4, 5, 6),
                        List.of(1, 2, 3, 4, 5, 6),
                        30,
                        6
                ),
                Arguments.of(
                        List.of(1, 2, 3, 4, 5, 6),
                        List.of(1, 2, 3, 4, 5, 7),
                        30,
                        5
                ),
                Arguments.of(
                        List.of(1, 2, 3, 4, 5, 6),
                        List.of(1, 2, 3, 4, 8, 7),
                        30,
                        4
                ),
                Arguments.of(
                        List.of(1, 2, 3, 4, 5, 6),
                        List.of(1, 2, 3, 9, 8, 7),
                        30,
                        3
                ),
                Arguments.of(
                        List.of(1, 2, 3, 4, 5, 6),
                        List.of(1, 2, 10, 9, 8, 7),
                        30,
                        2
                ),
                Arguments.of(
                        List.of(1, 2, 3, 4, 5, 6),
                        List.of(1, 11, 10, 9, 8, 7),
                        30,
                        1
                ),
                Arguments.of(
                        List.of(1, 2, 3, 4, 5, 6),
                        List.of(12, 11, 10, 9, 8, 7),
                        30,
                        0
                ),
                Arguments.of(
                        List.of(1, 2, 3, 4, 5, 6),
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
        WinningLotto myLotto = new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 6);

        //when
        boolean result = myLotto.hasBonusNumber();

        assertThat(result).isTrue();
    }
}
