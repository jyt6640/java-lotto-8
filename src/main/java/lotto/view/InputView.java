package lotto.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private static final String INPUT_PURCHASE_AMOUNT = "구입금액을 입력해 주세요.";
    private static final String INPUT_WINNING_NUBERS = "당첨 번호를 입력해 주세요.";
    private static final String INPUT_BONUS_NUBER = "보너스 번호를 입력해 주세요.";

    public String readPurchaseAmount() {
        return readInput(INPUT_PURCHASE_AMOUNT);
    }

    public String readWinningLottoNumbers() {
        return readInput(INPUT_WINNING_NUBERS);
    }

    public String readBonusNumber() {
        return readInput(INPUT_BONUS_NUBER);
    }

    private String readInput(String message) {
        System.out.println(message);
        return Console.readLine();
    }
}
