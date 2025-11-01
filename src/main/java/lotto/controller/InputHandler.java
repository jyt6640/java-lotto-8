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
        String price = inputview.readPurchaseAmount().strip();
        validateOnlyNumber(price);
        return Integer.parseInt(price);
    }

    public List<Integer> readWinningNumbers() {
        String winningNumbers = inputview.readWinningLottoNumbers().strip();
        return WinningNumberParser.parseWinningNumbers(winningNumbers);
    }

    public int readBonusNumber() {
        String bonusNumber = inputview.readBonusNumber().strip();
        return WinningNumberParser.parseBonusNumber(bonusNumber);
    }

    private void validateOnlyNumber(String price) {
        if (price.matches("^[0-9]+$")) {
            return;
        }
        throw new IllegalArgumentException("[ERROR] 구매 금액은 숫자만 입력 가능합니다.");
    }
}
