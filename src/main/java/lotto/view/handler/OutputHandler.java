package lotto.view.handler;

import java.util.List;
import lotto.dto.LottoPurchaseResult;
import lotto.dto.WinningStatistics;
import lotto.view.OutputView;

public class OutputHandler {
    private final OutputView outputView;

    public OutputHandler(OutputView outputView) {
        this.outputView = outputView;
    }

    public void showMyLottos(LottoPurchaseResult result) {
        int purchaseCount = result.getCount();
        List<List<Integer>> lottoNumbers = result.extractLottoNumbers();
        outputView.printMyLottos(purchaseCount, lottoNumbers);
    }

    public void showResult(List<WinningStatistics> results, double profitRate) {
        outputView.printResult(results, profitRate);
    }

    public void showError(IllegalArgumentException e) {
        outputView.printError(e);
    }
}
