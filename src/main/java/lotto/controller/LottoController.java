package lotto.controller;

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

        Lotto winngingLotto = inputController.getWinningNumbers();
        BonusNumber bonusNumber = inputController.getBonusNumber();
    }

    private Lotto generateLotto() {
        return new Lotto(lottoGenerator.generator());
    }
}
