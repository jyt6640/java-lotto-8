package lotto.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lotto.view.InputView;
import lotto.view.OutputView;

public class InputController {
    private final InputView inputView;
    private final OutputView outputView;


    public InputController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public int getMoney() {
        while (true) {
            try {
                return readMoney();
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e);
            }
        }
    }

    public List<Integer> getWinningNumbers() {
        while (true) {
            try {
                return readWinningNumbers();
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e);
            }
        }
    }

    public int getBonusNumber() {
        while (true) {
            try {
                return readBonusNumber();
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
