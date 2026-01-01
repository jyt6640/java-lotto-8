package lotto.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    public String readMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        String money = readLine();
        System.out.println();
        return money;
    }

    protected String readLine() {
        return Console.readLine();
    }

}
