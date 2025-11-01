package lotto.controller;

import java.util.List;
import lotto.util.WinningNumberParser;
import lotto.view.InputView;

public class InputHandler {
    private final InputView inputview;

    public InputHandler(InputView inputview) {
        this.inputview = inputview;
    }

    public int readPurchaseAmount() {
        String price = inputview.readPurchaseAmount();
        validateOnlyNumber(price);
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

    private void validateOnlyNumber(String price) {
        if (price.matches("^[0-9]+$")) {
            return;
        }
        throw new IllegalArgumentException("구매 금액은 숫자만 입력 가능합니다.");
    }
}
