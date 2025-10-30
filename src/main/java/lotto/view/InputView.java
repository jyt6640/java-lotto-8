package lotto.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private InputView() {
    }

    public static String readInput(String message) {
        System.out.println(message);
        return Console.readLine();
    }

    public static String readPurchaseAmount() {
        return readInput("구입금액을 입력해 주세요.");
    }

    public static String readWinningLottoNumbers() {
        return readInput("당첨 번호를 입력해 주세요.");
    }

    public static String readBonusNumber() {
        return readInput("보너스 번호를 입력해 주세요.");
    }
}
