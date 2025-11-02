package lotto.controller;

import static lotto.exception.ErrorMessage.INVALID_INPUT_NOT_BLANK;
import static lotto.exception.ErrorMessage.INVALID_INPUT_ONLY_NUMBER;
import static lotto.exception.ErrorMessage.INVALID_WINNING_NUMBER_FORMAT;

import java.util.List;
import lotto.util.InputParser;
import lotto.view.InputView;

public class InputHandler {
    private static final String WINNING_NUMBERS_FORMAT_REGEX = "^(\\d+)(\\s*,\\s*\\d+)*$";
    private static final String ONLY_NUMBER_REGEX = "^[0-9]+$";

    private final InputView inputview;

    public InputHandler(InputView inputview) {
        this.inputview = inputview;
    }

    public int readPurchaseAmount() {
        String price = inputview.readPurchaseAmount().strip();
        validateWhiteSpace(price);
        validateOnlyNumber(price);
        return InputParser.parseToInt(price);
    }

    public List<Integer> readWinningNumbers() {
        String winningNumbers = inputview.readWinningLottoNumbers().strip();
        validateWhiteSpace(winningNumbers);
        validateWinningNumberFormat(winningNumbers);
        return InputParser.parseWinningNumbers(winningNumbers);
    }

    public int readBonusNumber() {
        String bonusNumber = inputview.readBonusNumber().strip();
        validateWhiteSpace(bonusNumber);
        validateOnlyNumber(bonusNumber);
        return InputParser.parseToInt(bonusNumber);
    }

    private void validateOnlyNumber(String input) {
        if (input.matches(ONLY_NUMBER_REGEX)) {
            return;
        }
        throw new IllegalArgumentException(INVALID_INPUT_ONLY_NUMBER.toString());
    }

    private void validateWhiteSpace(String input) {
        if(input.isBlank()) {
            throw new IllegalArgumentException(INVALID_INPUT_NOT_BLANK.toString());
        }
    }

    private void validateWinningNumberFormat(String input) {
        if (!input.matches(WINNING_NUMBERS_FORMAT_REGEX)) {
            throw new IllegalArgumentException(INVALID_WINNING_NUMBER_FORMAT.toString());
        }
    }
}
