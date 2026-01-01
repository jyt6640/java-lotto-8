package lotto.controller;

import lotto.Lotto;
import lotto.view.OutputView;

public class LottoController {

    private InputController inputController;
    private OutputView outputView;

    public LottoController(InputController inputController, OutputView outputView) {
        this.inputController = inputController;
        this.outputView = outputView;
    }

    public void run() {
        int money = inputController.getMoney();
        Lotto winngingLotto = inputController.getWinningNumbers();
        int bonusNumber = inputController.getBonusNumber();
    }
}
