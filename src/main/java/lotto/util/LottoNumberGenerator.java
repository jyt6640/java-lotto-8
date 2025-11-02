package lotto.util;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;

public class LottoNumberGenerator {
    private static final int DEFAULT_COUNT = 6;
    private static final int MIN_RANDOM_RANGE = 1;
    private static final int MAX_RANDOM_RANGE = 45;

    public static List<Integer> getLottoNumber(int count) {
        return Randoms.pickUniqueNumbersInRange(MIN_RANDOM_RANGE, MAX_RANDOM_RANGE, count);
    }

    public static List<Integer> getLotto() {
        return getLottoNumber(DEFAULT_COUNT);
    }
}
