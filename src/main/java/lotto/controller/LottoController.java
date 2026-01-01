package lotto.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lotto.domain.BonusNumber;
import lotto.domain.Lotto;
import lotto.domain.Money;
import lotto.domain.Rank;
import lotto.domain.WinningLotto;
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

        Lotto winningNumbers = inputController.getWinningNumbers();
        BonusNumber bonusNumber = inputController.getBonusNumber();
        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);

        calculateRank(purchaseLotteries, winningLotto);

    }

    private List<Lotto> purchase(int purchaseCount) {
        List<Lotto> lotteries = new ArrayList<>();
        for (int i = 0; i < purchaseCount; i++) {
            lotteries.add(generateLotto());
        }
        return lotteries;
    }

    private Lotto generateLotto() {
        return new Lotto(lottoGenerator.generate());
    }

    private void printLotto(List<Lotto> purchaseLotteries) {
        List<List<Integer>> lotteries = new ArrayList<>();
        for (Lotto lotto : purchaseLotteries) {
            lotteries.add(lotto.getNumbers());
        }
        outputView.printPurchaseLotto(lotteries);
    }

    private Map<Rank, Integer> calculateRank(List<Lotto> lotteries, WinningLotto winningLotto) {
        Map<Rank, Integer> rankCount = initializeRankCount();
        for (Lotto lotto : lotteries) {
            Rank rank = winningLotto.determineRank(lotto);
            rankCount.put(rank, rankCount.get(rank) + 1);
        }
        return rankCount;
    }

    private Map<Rank, Integer> initializeRankCount() {
        Map<Rank, Integer> rankCount = new HashMap<>();
        for(Rank rank : Rank.values()) {
            rankCount.put(rank, 0);
        }
        return rankCount;
    }

}
