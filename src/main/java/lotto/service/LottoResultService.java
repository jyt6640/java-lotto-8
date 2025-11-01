package lotto.service;

import lotto.domain.Lotto;
import lotto.domain.Rank;
import lotto.domain.WinningLotto;

public class LottoResultService {
    public Rank getRank(WinningLotto winningLotto, Lotto lotto) {
        int matchCount = winningLotto.countMatches(lotto);
        boolean hasBonusNumber = winningLotto.hasBonusNumber(lotto);

        return Rank.of(matchCount, hasBonusNumber);
    }
}
