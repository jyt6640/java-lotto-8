package lotto.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Lotteries {

    private List<Lotto> lotteries;

    public Lotteries(List<Lotto> lotteries) {
        this.lotteries = lotteries;
    }

    public Map<Rank, Integer> getRanks(Lotto winningLotto, BonusNumber bonusNumber) {
        Map<Rank, Integer> ranks = initializeRank();
        for (Lotto lotto : lotteries) {
            int matchCount = lotto.compareWinningLotto(winningLotto);
            boolean hasBonusNumber = lotto.hasBonusNumber(bonusNumber);
            Rank lottoRank = Rank.of(matchCount, hasBonusNumber);
            ranks.put(lottoRank, ranks.get(lottoRank) + 1);
        }
        return ranks;
    }

    private Map<Rank, Integer> initializeRank() {
        Map<Rank, Integer> ranks = new HashMap<>();
        for (Rank rank : Rank.values()) {
            ranks.put(rank, 0);
        }
        return ranks;
    }

    public List<Lotto> getLotteries() {
        return lotteries;
    }
}
