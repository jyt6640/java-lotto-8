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
        validateWhiteSpace(price);
        validateOnlyNumber(price);
        return Integer.parseInt(price);
    }

    public List<Integer> readWinningNumbers() {
        String winningNumbers = inputview.readWinningLottoNumbers().strip();
        validateWhiteSpace(winningNumbers);
        validateWinningNumberFormat(winningNumbers);
        return WinningNumberParser.parseWinningNumbers(winningNumbers);
    }

    public int readBonusNumber() {
        String bonusNumber = inputview.readBonusNumber().strip();
        validateWhiteSpace(bonusNumber);
        validateOnlyNumber(bonusNumber);
        return WinningNumberParser.parseBonusNumber(bonusNumber);
    }

    private void validateOnlyNumber(String input) {
        if (input.matches("^[0-9]+$")) {
            return;
        }
        throw new IllegalArgumentException("[ERROR] 숫자만 입력 가능합니다.");
    }

    private void validateWhiteSpace(String input) {
        if(input.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 입력값은 공백일 수 없습니다.");
        }
    }

    private void validateWinningNumberFormat(String input) {
        if (!input.matches("^(\\d+)(\\s*,\\s*\\d+)*$")) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 쉼표(,)로 구분된 숫자 형식이어야 합니다.");
        }
    }
}
