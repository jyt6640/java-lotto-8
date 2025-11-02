package lotto.service;

import java.util.Map;
import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.domain.Rank;
import lotto.domain.WinningLotto;
import lotto.dto.LottoPurchaseResult;

public class LottoGameService {
    private final LottoPurchaseService purchaseService;
    private final LottoResultService resultService;

    public LottoGameService(
            LottoPurchaseService purchaseService,
            LottoResultService resultService
    ) {
        this.purchaseService = purchaseService;
        this.resultService = resultService;
    }

    public LottoPurchaseResult purchaseLottos(int amount) {
        return purchaseService.purchase(amount);
    }

    public Map<Rank, Integer> calculateStatistics(WinningLotto winningLotto, Lottos lottos) {
        return resultService.getStatistics(winningLotto, lottos);
    }

    public double calculateProfitRate(Map<Rank, Integer> statistics, Money money) {
        return resultService.calculateProfitRate(statistics, money);
    }
}
