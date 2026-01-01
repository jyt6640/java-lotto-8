package lotto.controller;

import lotto.domain.BonusNumber;
import lotto.domain.Lotto;
import lotto.domain.Money;
import lotto.view.OutputView;

public class LottoController {

    private InputController inputController;
    private OutputView outputView;

    public LottoController(InputController inputController, OutputView outputView) {
        this.inputController = inputController;
        this.outputView = outputView;
    }

    public void run() {
        Money money = inputController.getMoney();
        Lotto winngingLotto = inputController.getWinningNumbers();
        BonusNumber bonusNumber = inputController.getBonusNumber();
    }
}
