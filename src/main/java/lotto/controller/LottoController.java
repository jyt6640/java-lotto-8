package lotto.controller;

import camp.nextstep.edu.missionutils.Console;
import java.util.Map;
import java.util.function.Supplier;
import lotto.domain.BonusNumber;
import lotto.domain.Lotto;
import lotto.domain.Rank;
import lotto.domain.WinningLotto;
import lotto.service.LottoPurchaseService;
import lotto.service.LottoResultService;
import lotto.dto.LottoPurchaseResult;
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
            LottoPurchaseResult result = retryOnException(() -> {
                int purchaseAmount = inputHandler.readPurchaseAmount();
                return lottoPurchaseService.purchase(purchaseAmount);
            });
            outputHandler.showMyLottos(result);

            WinningLotto winningLotto = createWinningLottoSafely();

            Map<Rank, Integer> statistics = lottoResultService.getStatistics(winningLotto, result.getLottos());
            double profitRate = lottoResultService.calculateProfitRate(statistics, result.getMoney());

            outputHandler.showResult(statistics, profitRate);
        } finally {
            Console.close();
        }
    }

    private <T> T retryOnException(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                outputHandler.showError(e);
            }
        }
    }

    private WinningLotto createWinningLottoSafely() {
        Lotto winningNumbers = retryOnException(() ->
                new Lotto(inputHandler.readWinningNumbers())
        );

        BonusNumber bonusNumber = retryOnException(() ->
                new BonusNumber(inputHandler.readBonusNumber(), winningNumbers)
        );

        return new WinningLotto(winningNumbers, bonusNumber);
    }
}
