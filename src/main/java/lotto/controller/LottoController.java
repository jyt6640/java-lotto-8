package lotto.controller;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import java.util.Map;
import lotto.domain.Rank;
import lotto.domain.WinningLotto;
import lotto.service.LottoMachine;
import lotto.service.LottoResultService;
import lotto.service.dto.LottoPurchaseResult;

public class LottoController {
    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;
    private final LottoMachine lottoMachine;
    private final LottoResultService lottoResultService;

    public LottoController() {
        this.inputHandler = new InputHandler();
        this.outputHandler = new OutputHandler();
        this.lottoMachine = new LottoMachine();
        this.lottoResultService = new LottoResultService();
    }

    public void run() {
        try {
            int purchaseAmount = inputHandler.readPurchaseAmount();
            LottoPurchaseResult result = lottoMachine.purchase(purchaseAmount);
            outputHandler.printMyLottos(result);

            List<Integer> winningNumbers = inputHandler.readWinningNumbers();
            int bonusNumber = inputHandler.readBonusNumber();
            WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);

            Map<Rank, Integer> statistics = lottoResultService.getStatistics(winningLotto, result.getLottos());
            double profitRate = lottoResultService.calculateProfitRate(statistics, purchaseAmount);

            outputHandler.printResult(statistics, profitRate);
        } finally {
            Console.close();
        }
    }
}
