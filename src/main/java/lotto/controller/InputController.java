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

    public Money getMoney() {
        while (true) {
            try {
                return new Money(readMoney());
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e);
            }
        }
    }

    public Lotto getWinningNumbers() {
        while (true) {
            try {
                return new Lotto(readWinningNumbers());
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

    private int readMoney() {
        String input = inputView.readMoney();
        return Integer.parseInt(input);
    }

    private List<Integer> readWinningNumbers() {
        String input = inputView.readWinningNumbers();
        return Arrays.stream(input.split(","))
                .map(Integer::parseInt)
                .toList();
    }

    private int readBonusNumber() {
        String input = inputView.readBonusNumber();
        return Integer.parseInt(input);
    }

}
