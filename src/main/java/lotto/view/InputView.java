package lotto.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    public String readMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        String money = readLine();
        System.out.println();
        return money;
    }

    public String readWinningNumbers() {
        System.out.println("당첨 번호를 입력해 주세요.");
        String winningNumbers = readLine();
        System.out.println();
        return winningNumbers;
    }

    public String readBonusNumber() {
        System.out.println("보너스 번호를 입력해 주세요.");
        String bonusNumber = readLine();
        System.out.println();
        return bonusNumber;
    }

    protected String readLine() {
        return Console.readLine();
    }

}
