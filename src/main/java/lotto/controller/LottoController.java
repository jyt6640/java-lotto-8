package lotto.controller;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import java.util.function.Supplier;
import lotto.domain.BonusNumber;
import lotto.domain.Lotto;
import lotto.domain.WinningLotto;
import lotto.dto.WinningStatistics;
import lotto.service.LottoGameService;
import lotto.dto.LottoPurchaseResult;
import lotto.view.handler.InputHandler;
import lotto.view.handler.OutputHandler;


public class LottoController {
    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;
    private final LottoGameService gameService;

    public LottoController(
            InputHandler inputHandler,
            OutputHandler outputHandler,
            LottoGameService gameService
    ) {
        this.inputHandler = inputHandler;
        this.outputHandler = outputHandler;
        this.gameService = gameService;
    }

    public void run() {
        try {
            LottoPurchaseResult result = purchaseLottosWithRetry();
            outputHandler.showMyLottos(result);

            WinningLotto winningLotto = createWinningLottoSafely();

            showGameResult(result, winningLotto);
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

    private LottoPurchaseResult purchaseLottosWithRetry() {
        return retryOnException(() -> {
            int purchaseAmount = inputHandler.readPurchaseAmount();
            return gameService.purchaseLottos(purchaseAmount);
        });
    }

    private void showGameResult(LottoPurchaseResult result, WinningLotto winningLotto) {
        List<WinningStatistics> results = gameService.getWinningStatistics(winningLotto, result.getLottos());

        double profitRate = gameService.calculateProfitRate(
                gameService.calculateStatistics(winningLotto, result.getLottos()),
                result.getMoney()
        );

        outputHandler.showResult(results, profitRate);
    }
}
