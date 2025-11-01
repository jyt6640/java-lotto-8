package lotto.controller;

import camp.nextstep.edu.missionutils.Console;
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

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;
    private final LottoMachine lottoMachine;
    private final LottoResultService lottoResultService;

    public LottoController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.lottoMachine = new LottoMachine();
        this.lottoResultService = new LottoResultService();
    }

    public void run() {
        try {
            String purchaseAmountStr = inputView.readPurchaseAmount();
            int purchaseAmount = Integer.parseInt(purchaseAmountStr);

            Lottos myLottos = lottoMachine.purchase(purchaseAmount);
            outputView.printMyLottos(purchaseAmount, myLottos);

            String winningNumbersStr = inputView.readWinningLottoNumbers();
            List<Integer> winningNumbers = WinningNumberParser.parseWinningNumbers(winningNumbersStr);
            String bonusNumberStr = inputView.readBonusNumber();
            int bonusNumber = WinningNumberParser.parseBonusNumber(bonusNumberStr);

            WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);

            Map<Rank, Integer> statistics = lottoResultService.getStatistics(winningLotto, myLottos);
            double profitRate = lottoResultService.calculateProfitRate(statistics, purchaseAmount);

            outputView.printResult(statistics, profitRate);
        } catch (IllegalArgumentException e) {
            throw e;
        } finally {
            Console.close();
        }
    }
}
