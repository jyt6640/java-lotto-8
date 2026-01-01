package lotto.util;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.Collections;
import java.util.List;

public class LottoNumberGenerator implements NumberGenerator {
    @Override
    public List<Integer> generate() {
        List<Integer> lotto = Randoms.pickUniqueNumbersInRange(1,45,6);
        Collections.sort(lotto);
        return lotto;
    }
}
