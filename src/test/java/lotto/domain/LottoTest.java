package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static lotto.exception.ErrorMessage.INVALID_NUMBER_RANGE;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

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

    @DisplayName("번호가 1에서 45 범위를 벗어나면 예외 발생")
    @ParameterizedTest
    @ValueSource(ints = {0, -1, 46, 50, 100})
    void 번호가_범위를_벗어나면_예외_발생(int invalidNumber) {
        //when&then
        assertThatThrownBy(() -> new Lotto(List.of(invalidNumber, 2, 3, 4, 5, 6)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_NUMBER_RANGE.toString());
    }
    
    @DisplayName("로또 번호 오름차순 정렬")
    @Test
    void 로또_번호_오름차순_정렬() {
        //given
        Lotto input = new Lotto(List.of(7, 6, 5, 4, 3, 2));

        //when
        List<Integer> result = input.getNumbers();

        //then
        assertThat(result).containsExactly(2, 3, 4, 5, 6, 7);
    }
}
