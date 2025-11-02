package lotto.domain;

import static lotto.domain.exception.ErrorMessage.INVALID_LOTTOS_SIZE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottosTest {
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
}
