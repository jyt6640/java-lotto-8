package lotto.controller;

import lotto.view.OutputView;

public class LottoController {

    private final InputController inputController;
    private final OutputView outputView;

    public LottoController(InputController inputController, OutputView outputView) {
        this.inputController = inputController;
        this.outputView = outputView;
    }

    public void run() {

    }
}
