package lotto.controller;

import java.util.Map;
import lotto.domain.Lottos;
import lotto.domain.Rank;
import lotto.service.dto.LottoPurchaseResult;
import lotto.view.OutputView;

public class OutputHandler {
    private OutputView outputView = new OutputView();

    public void printMyLottos(LottoPurchaseResult result) {
        int purchaseCount = result.getCount();
        Lottos lottos = result.getLottos();
        outputView.printMyLottos(purchaseCount, lottos);
    }

    public void printResult(Map<Rank, Integer> statistics, double profitRate) {
        outputView.printResult(statistics, profitRate);
    }
}
