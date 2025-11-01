package lotto.controller;

import java.util.Map;
import lotto.domain.Lottos;
import lotto.domain.Rank;
import lotto.view.OutputView;

public class OutputHandler {
    private OutputView outputView = new OutputView();

    public void printMyLottos(int purchaseAmount, Lottos myLottos) {
        outputView.printMyLottos(purchaseAmount, myLottos);
    }

    public void printResult(Map<Rank, Integer> statistics, double profitRate) {
        outputView.printResult(statistics, profitRate);
    }
}
