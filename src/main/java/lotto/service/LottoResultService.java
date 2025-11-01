package lotto.service;

import java.util.EnumMap;
import java.util.Map;
import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.domain.Rank;
import lotto.domain.WinningLotto;

public class LottoResultService {
    public Rank getRank(WinningLotto winningLotto, Lotto lotto) {
        int matchCount = winningLotto.countMatches(lotto);
        boolean hasBonusNumber = winningLotto.hasBonusNumber(lotto);

        return Rank.of(matchCount, hasBonusNumber);
    }

    public Map<Rank, Integer> getStatistics(WinningLotto winningLotto, Lottos lottos) {
        Map<Rank, Integer> statistics = initializeStatistics();
        for (Lotto lotto : lottos.getLottos()) {
            Rank rank = getRank(winningLotto, lotto);
            statistics.put(rank, statistics.get(rank) + 1);
        }

        return statistics;
    }

    private Map<Rank, Integer> initializeStatistics() {
        Map<Rank, Integer> statistics = new EnumMap<>(Rank.class);
        for (Rank rank : Rank.values()) {
            statistics.put(rank, 0);
        }

        return statistics;
    }

    public double calculateProfitRate(Map<Rank, Integer> statistics, int totalPurchaseAmount) {
        long totalPrize = 0;

        for(Map.Entry<Rank, Integer> entry : statistics.entrySet()) {
            Rank rank = entry.getKey();
            int count = entry.getValue();
            totalPrize += rank.getPrize() * count;
        }

        return (double) totalPrize / totalPurchaseAmount;
    }
}
