package lotto.controller;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.BonusNumber;
import lotto.domain.Lotto;
import lotto.domain.Money;
import lotto.util.NumberGenerator;
import lotto.view.OutputView;

public class LottoController {

    private InputController inputController;
    private OutputView outputView;
    private NumberGenerator lottoGenerator;

    public LottoController(InputController inputController, OutputView outputView, NumberGenerator lottoGenerator) {
        this.inputController = inputController;
        this.outputView = outputView;
        this.lottoGenerator = lottoGenerator;
    }

    public void run() {
        Money money = inputController.getMoney();
        int purchaseCount = money.purchaseCount();
        List<Lotto> purchaseLotteries = purchase(purchaseCount);
        printLotto(purchaseLotteries);

        Lotto winngingLotto = inputController.getWinningNumbers();
        BonusNumber bonusNumber = inputController.getBonusNumber();

        List<Integer> matchCount = matchCountResult(winngingLotto, purchaseLotteries);
    }

    private List<Lotto> purchase(int purchaseCount) {
        List<Lotto> lotteries = new ArrayList<>();
        for (int i = 0; i < purchaseCount; i++) {
            lotteries.add(generateLotto());
        }
        return lotteries;
    }

    private Lotto generateLotto() {
        return new Lotto(lottoGenerator.generator());
    }

    private void printLotto(List<Lotto> purchaseLotteries) {
        List<List<Integer>> lotteries = new ArrayList<>();
        for (Lotto lotto : purchaseLotteries) {
            lotteries.add(lotto.getNumbers());
        }
        outputView.printPurchaseLotto(lotteries);
    }

    private List<Integer> matchCountResult(Lotto winningLotto, List<Lotto> purchasedLotteries) {
        List<Integer> winningNumbers = winningLotto.getNumbers();
        return purchasedLotteries.stream()
                .map(lotto -> matchCount(lotto, winningNumbers))
                .toList();
    }

    private int matchCount(Lotto lotto, List<Integer> winningNumbers) {
        return (int) lotto.getNumbers().stream()
                .filter(winningNumbers::contains)
                .count();
    }
}
