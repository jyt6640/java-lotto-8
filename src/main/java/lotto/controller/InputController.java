package lotto.controller;

import java.util.Arrays;
import java.util.List;
import lotto.domain.BonusNumber;
import lotto.domain.Lotto;
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

    public Lotto getWinningLotto() {
        while (true) {
            try {
                return new Lotto(readWinningLotto());
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e);
            }
        }
    }

    public BonusNumber getBonusNumber() {
        while (true) {
            try {
                return new BonusNumber(readBonusNumber());
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e);
            }
        }
    }

    private int readPurchaseAmount() {
        String value = inputView.readPurchaseAmount();
        return Integer.parseInt(value);
    }

    private List<Integer> readWinningLotto() {
        String value = inputView.readWinningLotto();
        return Arrays.stream(value.split(","))
                .map(String::strip)
                .mapToInt(Integer::parseInt)
                .boxed()
                .toList();
    }

    private int readBonusNumber() {
        String value = inputView.readBonusNumber();
        return Integer.parseInt(value);
    }

}
