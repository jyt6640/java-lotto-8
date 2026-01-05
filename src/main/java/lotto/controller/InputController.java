package lotto.controller;

import lotto.domain.Money;
import lotto.view.InputView;
import lotto.view.OutputView;

public class InputController {
    private final InputView inputView;
    private final OutputView outputView;


    public InputController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public Money getPurchaseAmount() {
        while (true) {
            try {
                return new Money(readPurchaseAmount());
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e);
            }
        }
    }

    private int readPurchaseAmount() {
        String value = inputView.readPurchaseAmount();
        return Integer.parseInt(value);
    }

}
