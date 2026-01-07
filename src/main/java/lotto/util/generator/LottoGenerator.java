package lotto.util.generator;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.domain.Lotto;

public class LottoGenerator implements NumberGenerator {

    @Override
    public Lotto generate() {
        return new Lotto(
                Randoms.pickUniqueNumbersInRange(1, 45, 6)
                        .stream()
                        .sorted()
                        .toList());
    }
}
