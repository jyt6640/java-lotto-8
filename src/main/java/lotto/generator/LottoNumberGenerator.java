package lotto.generator;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;

public class LottoNumberGenerator implements NumberGenerator {
    private static final int LOTTO_NUMBER_COUNT = 6;
    private static final int MIN_RANDOM_RANGE = 1;
    private static final int MAX_RANDOM_RANGE = 45;

    @Override
    public List<Integer> generate() {
        return Randoms.pickUniqueNumbersInRange(
                MIN_RANDOM_RANGE,
                MAX_RANDOM_RANGE,
                LOTTO_NUMBER_COUNT
        );
    }
}
