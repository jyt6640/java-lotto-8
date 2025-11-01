package lotto.util;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;

public class LottoNumberGenerator {
    private static final int DEFAULT_COUNT = 6;

    public static List<Integer> getLottoNumber(int count) {
        return Randoms.pickUniqueNumbersInRange(1,45, count);
    }

    public static List<Integer> getLotto() {
        return getLottoNumber(DEFAULT_COUNT);
    }
}
