package lotto;

import java.util.List;
import java.util.Map;
import lotto.domain.Lottos;
import lotto.domain.Rank;
import lotto.domain.WinningLotto;
import lotto.service.LottoMachine;
import lotto.service.LottoResultService;
import lotto.util.WinningNumberParser;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {
    public static void main(String[] args) {
        LottoMachine machine = new LottoMachine();
        LottoResultService resultService = new LottoResultService();

        String purchaseAmountStr = InputView.readPurchaseAmount();
        int purchaseAmount = Integer.parseInt(purchaseAmountStr);

        Lottos myLottos = machine.purchase(purchaseAmount);
        OutputView.printMyLottos(purchaseAmount, myLottos);

        String winningNumbersStr = InputView.readWinningLottoNumbers();
        List<Integer> winningNumbers = WinningNumberParser.parseWinningNumbers(winningNumbersStr);
        String bonusNumberStr = InputView.readBonusNumber();
        int bonusNumber = WinningNumberParser.parseBonusNumber(bonusNumberStr);

        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);

        Map<Rank, Integer> statistics = resultService.getStatistics(winningLotto, myLottos);
        double profitRate = resultService.calculateProfitRate(statistics, purchaseAmount);

        OutputView.printResult(statistics, profitRate);
    }
}
