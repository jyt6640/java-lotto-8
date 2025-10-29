package lotto.util;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import Util.RandomUtil;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RandomUtilTest {
    @DisplayName("로또 번호의 숫자가 1에서 45 범위 내에 있는지 확인")
    @Test
    void 로또_번호의_숫자가_1에서_45_범위_내에_있는지_확인() {
        //given
        List<Integer> input = RandomUtil.getLottoNumber(1);

        //when&then
        assertThat(input.getFirst()).isBetween(1, 45);
    }

    @DisplayName("로또 번호 6개 뽑기")
    @Test
    void 로또_번호_6개_뽑기() {
        //given
        List<Integer> input = RandomUtil.getLotto();

        //when&then
        assertThat(input.size()).isEqualTo(6);
    }

    @DisplayName("하나의 로또를 발행 할 때 중복되지 않는 숫자 발행")
    @Test
    void 하나의_로또를_발행_할_때_중복되지_않는_숫자_발행() {
        //given
        List<Integer> input = RandomUtil.getLotto();
        Set<Integer> uniqueNumbers = new HashSet<>();

        //when
        uniqueNumbers.addAll(input);

        //then
        assertThat(uniqueNumbers.size()).isEqualTo(6);
    }

    @DisplayName("여섯 개의 당첨 번호 및 보너스 번호 뽑기")
    @Test
    void 여섯_개의_당첨_번호_및_보너스_번호_뽑기() {
        //given
        List<Integer> input = RandomUtil.getWinningNumbers(7);

        //when&then
        assertThat(input.size()).isEqualTo(7);
    }

    @DisplayName("중복되지 않는 당첨 번호 및 보너스 번호 발행")
    @Test
    void 중복되지_않는_당첨_번호_및_보너스_번호_발행() {
        //given
        List<Integer> input = RandomUtil.getWinningLotto();
        Set<Integer> uniqueNumbers = new HashSet<>();

        //when
        uniqueNumbers.addAll(input);

        //then
        assertThat(uniqueNumbers.size()).isEqualTo(7);
    }
}
