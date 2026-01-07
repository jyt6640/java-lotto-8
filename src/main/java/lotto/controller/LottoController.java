package lotto.controller;

import java.util.List;
import java.util.Map;
import lotto.domain.BonusNumber;
import lotto.domain.Lotteries;
import lotto.domain.Lotto;
import lotto.domain.Money;
import lotto.domain.Rank;
import lotto.service.PurchaseService;
import lotto.view.OutputView;

public class LottoController {

    private final InputController inputController;
    private final OutputView outputView;
    private final PurchaseService purchaseService;

    public LottoController(InputController inputController, OutputView outputView, PurchaseService purchaseService) {
        this.inputController = inputController;
        this.outputView = outputView;
        this.purchaseService = purchaseService;
    }

    public void run() {
        Money money = inputController.getPurchaseAmount();
        Lotteries lotteries = new Lotteries(generateLotteries(money));
        printLotteries(lotteries, money);

        Lotto winningLotto = inputController.getWinningLotto();
        BonusNumber bonusNumber = inputController.getBonusNumber();

        Map<Rank, Integer> ranks = lotteries.getRanks(winningLotto, bonusNumber);
        double totalPrize = calculateStatics(ranks);
        double profitRate = (totalPrize / money.getMoney()) * 100;

        outputView.printStatistics(ranks, profitRate);

    }

    private List<Lotto> generateLotteries(Money money) {
        return purchaseService.generateLotto(money);
    }

    private void printLotteries(Lotteries lotteries, Money money) {
        outputView.printLotteriesPrefix(money.getCount());
        for (Lotto lotto : lotteries.getLotteries()) {
            outputView.printLotteries(lotto.getNumbers());
        }
    }

    public double calculateStatics(Map<Rank, Integer> ranks) {
        double totalPrize = 0;
        for (Map.Entry<Rank, Integer> entry : ranks.entrySet()) {
            totalPrize += entry.getKey().getPrize() * entry.getValue();
        }
        return totalPrize;
    }

}
