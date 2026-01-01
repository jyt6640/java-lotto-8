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
        purchase(purchaseCount);

        Lotto winngingLotto = inputController.getWinningNumbers();
        BonusNumber bonusNumber = inputController.getBonusNumber();
    }

    private List<Lotto> purchase(int purchaseCount) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < purchaseCount; i++) {
            lottos.add(generateLotto());
        }
        return lottos;
    }

    private Lotto generateLotto() {
        return new Lotto(lottoGenerator.generator());
    }
}
