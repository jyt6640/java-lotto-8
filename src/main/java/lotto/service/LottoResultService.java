package lotto.service;

import java.util.Map;
import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.domain.Rank;
import lotto.domain.WinningLotto;

public class LottoResultService {
    public Map<Rank, Integer> getStatistics(WinningLotto winningLotto, Lottos lottos) {
        return lottos.calculateStatistics(winningLotto);
    }

    public double calculateProfitRate(Map<Rank, Integer> statistics, Money money) {
        long totalPrize = 0;
        for(Map.Entry<Rank, Integer> entry : statistics.entrySet()) {
            Rank rank = entry.getKey();
            int count = entry.getValue();
            totalPrize += rank.getPrize() * count;
        }
        return money.calculateProfitRate(totalPrize);
    }
}
