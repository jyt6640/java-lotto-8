package lotto.controller;

import java.util.List;
import lotto.util.WinningNumberParser;
import lotto.view.InputView;

public class InputHandler {
    private final InputView inputview = new InputView();

    public int readPurchaseAmount() {
        String price = inputview.readPurchaseAmount();
        return Integer.parseInt(price);
    }

    public List<Integer> readWinningNumbers() {
        String winningNumbers = inputview.readWinningLottoNumbers();
        return WinningNumberParser.parseWinningNumbers(winningNumbers);
    }

    public int readBonusNumber() {
        String bonusNumber = inputview.readBonusNumber();
        return WinningNumberParser.parseBonusNumber(bonusNumber);
    }
}
