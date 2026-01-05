package lotto.controller;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.Money;
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
        List<Lotto> lotteries = generateLotteries(money);
        printLotteries(lotteries, money);

    }

    private List<Lotto> generateLotteries(Money money) {
        return purchaseService.generateLotto(money);
    }

    private void printLotteries(List<Lotto> lotteries, Money money) {
        outputView.printLotteriesPrefix(money.getCount());
        for (Lotto lotto : lotteries) {
            outputView.printLotteries(lotto.getNumbers());
        }
    }

}
