package lotto.controller;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import lotto.domain.Rank;
import lotto.domain.WinningLotto;
import lotto.service.LottoPurchaseService;
import lotto.service.LottoResultService;
import lotto.service.dto.LottoPurchaseResult;
import lotto.view.InputView;

public class LottoController {
    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;
    private final LottoPurchaseService lottoPurchaseService;
    private final LottoResultService lottoResultService;
    private final InputView inputView;

    public LottoController() {
        this.inputView = new InputView();
        this.inputHandler = new InputHandler(inputView);
        this.outputHandler = new OutputHandler();
        this.lottoPurchaseService = new LottoPurchaseService();
        this.lottoResultService = new LottoResultService();
    }

    public void run() {
        try {
            int purchaseAmount = retryOnException(inputHandler::readPurchaseAmount);
            LottoPurchaseResult result = lottoPurchaseService.purchase(purchaseAmount);
            outputHandler.printMyLottos(result);

            WinningLotto winningLotto = createWinningLottoSafely();

            Map<Rank, Integer> statistics = lottoResultService.getStatistics(winningLotto, result.getLottos());
            double profitRate = lottoResultService.calculateProfitRate(statistics, purchaseAmount);

            outputHandler.printResult(statistics, profitRate);
        } finally {
            Console.close();
        }
    }

    private <T> T retryOnException(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private WinningLotto createWinningLottoSafely() {
        while (true) {
            try {
                List<Integer> winningNumbers = inputHandler.readWinningNumbers();
                int bonusNumber = inputHandler.readBonusNumber();
                return new WinningLotto(winningNumbers, bonusNumber);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
