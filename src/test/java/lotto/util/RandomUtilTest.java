package lotto.util;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import Util.RandomUtil;
import java.util.List;
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
}
