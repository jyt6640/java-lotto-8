package lotto.view.handler;

import java.util.List;
import java.util.Map;
import lotto.domain.Rank;
import lotto.dto.LottoPurchaseResult;
import lotto.dto.WinningStatistics;
import lotto.dto.mapper.OutputMapper;
import lotto.view.OutputView;

public class OutputHandler {
    private final OutputView outputView;
    private final OutputMapper outputMapper;

    public OutputHandler(OutputView outputView, OutputMapper outputMapper) {
        this.outputView = outputView;
        this.outputMapper = outputMapper;
    }

    public void showMyLottos(LottoPurchaseResult result) {
        int purchaseCount = result.getCount();
        List<List<Integer>> lottoNumbers = result.extractLottoNumbers();
        outputView.printMyLottos(purchaseCount, lottoNumbers);
    }

    public void showResult(Map<Rank, Integer> statistics, double profitRate) {
        List<WinningStatistics> results = outputMapper.mapToWinningStatistics(statistics);
        outputView.printResult(results, profitRate);
    }

    public void showError(IllegalArgumentException e) {
        outputView.printError(e);
    }
}
