package lotto.service;

import java.util.List;
import java.util.Map;
import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.domain.Rank;
import lotto.domain.WinningLotto;
import lotto.dto.LottoPurchaseResult;
import lotto.dto.WinningStatistics;
import lotto.service.mapper.OutputMapper;

public class LottoGameService {
    private final LottoPurchaseService purchaseService;
    private final LottoResultService resultService;
    private final OutputMapper outputMapper;

    public LottoGameService(
            LottoPurchaseService purchaseService,
            LottoResultService resultService,
            OutputMapper outputMapper
    ) {
        this.purchaseService = purchaseService;
        this.resultService = resultService;
        this.outputMapper = outputMapper;
    }

    public LottoPurchaseResult purchaseLottos(int amount) {
        return purchaseService.purchase(amount);
    }

    public Map<Rank, Integer> calculateStatistics(WinningLotto winningLotto, Lottos lottos) {
        return resultService.getStatistics(winningLotto, lottos);
    }

    public List<WinningStatistics> getWinningStatistics(WinningLotto winningLotto, Lottos lottos) {
        Map<Rank, Integer> statistics = resultService.getStatistics(winningLotto, lottos);
        return outputMapper.mapToWinningStatistics(statistics);
    }

    public double calculateProfitRate(Map<Rank, Integer> statistics, Money money) {
        return resultService.calculateProfitRate(statistics, money);
    }
}
